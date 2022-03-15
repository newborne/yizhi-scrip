package com.yizhi.spark;

import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.bson.Document;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import scala.Tuple2;

import java.io.InputStream;
import java.util.*;

/**
 * The type Spark material recommend.
 */
public class SparkMaterialRecommend {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        //加载外部的配置文件，spark.properties
        InputStream inputStream = SparkMaterialRecommend.class.getClassLoader().getResourceAsStream("spark.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        //构建Spark配置
        SparkConf sparkConf = new SparkConf()
                .setAppName("SparkMaterialRecommend")
                .setMaster("local[*]")
                .set("spark.mongodb.input.uri", properties.getProperty("spark.mongodb.input.material.uri"));
        //构建Spark上下文
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        //加载MongoDB中的数据
        JavaMongoRDD<Document> rdd = MongoSpark.load(jsc);
        //在数据中会存在，同一个用户对不同的动态（相同动态）进行操作，需要合并操作
        JavaRDD<Document> values = rdd.mapToPair(document -> {
            Long userId = document.getLong("userId");
            Long materialRid = document.getLong("materialRid");
            return new Tuple2<>(userId + "_" + materialRid, document);
        }).reduceByKey((v1, v2) -> {
            double newScore = v1.getDouble("rating") + v2.getDouble("rating");
            v1.put("rating", newScore);
            return v1;
        }).values();
        //用户列表
        List<Long> userIdList = rdd.map(v1 -> v1.getLong("userId")).distinct().collect();
        JavaPairRDD<Long, Rating> ratings = values.mapToPair(document -> {
            Long created = document.getLong("created");
            int userId = document.getLong("userId").intValue();
            int materialRid = document.getLong("materialRid").intValue();
            Double rating = document.getDouble("rating");
            System.out.println(Thread.currentThread()
                    .getStackTrace()[1].getMethodName() + "方法：" + "created:" + created + "userId:" + userId + "materialRid:" + materialRid + "rating:" + rating);
            Rating r = new Rating(userId, materialRid, rating);
            return new Tuple2<>(created % 10, r);
        });
        SparkRecommendModel model = new SparkRecommendModel();
        MatrixFactorizationModel bestModel = model.model(ratings);
        //连接redis，做存储
        String redisNodesStr = properties.getProperty("redis.cluster.nodes");
        String[] redisNodesStrs = StringUtils.split(redisNodesStr, ',');
        Set<HostAndPort> nodes = new HashSet<>();
        for (String nodesStr : redisNodesStrs) {
            String[] ss = StringUtils.split(nodesStr, ':');
            nodes.add(new HostAndPort(ss[0], Integer.parseInt(ss[1])));
        }
        String redisPassword = properties.getProperty("redis.cluster.password");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(5000);
        config.setMaxIdle(100);
        config.setMinIdle(10);
        JedisCluster jedisCluster = new JedisCluster(nodes, 5000, 5000, 5, redisPassword, config);
        for (Long userId : userIdList) {
            // 推荐num个
            Rating[] recommendProducts = bestModel.recommendProducts(userId.intValue(), 10);
            List<Integer> products = new ArrayList<>();
            for (Rating product : recommendProducts) {
                products.add(product.product());
            }
            String key = "RECOMMEND_MATERIAL_" + userId;
            jedisCluster.set(key, StringUtils.join(products, ','));
        }
        //关闭
        jedisCluster.close();
        jsc.close();
    }
}
