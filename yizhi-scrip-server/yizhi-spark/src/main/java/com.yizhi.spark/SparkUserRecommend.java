package com.yizhi.spark;

import com.mongodb.spark.MongoSpark;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import scala.Tuple2;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class SparkUserRecommend {
    public static void main(String[] args) throws Exception {
        //加载外部的配置文件，spark.properties
        InputStream inputStream = SparkUserRecommend.class.getClassLoader().getResourceAsStream("spark.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        //构建Spark配置
        SparkConf sparkConf = new SparkConf()
                .setAppName("SparkUserRecommend")
                .setMaster("local[*]")
                .set("spark.mongodb.output.uri", properties.getProperty("spark.mongodb.output.user.uri"));
        //加载mysql数据
        SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
        String url = properties.getProperty("jdbc.url");
        // 设置数据库连接信息
        Properties connectionProperties = new Properties();
        connectionProperties.put("driver", properties.getProperty("jdbc.driver-class-name"));
        connectionProperties.put("user", properties.getProperty("jdbc.username"));
        connectionProperties.put("password", properties.getProperty("jdbc.password"));
        JavaRDD<Row> userInfoRdd = sparkSession.read().jdbc(url, "ap_user_info", connectionProperties).toJavaRDD();
        //用户列表
        List<Long> userIds = userInfoRdd.map(v -> v.getLong(1)).collect();
        //计算出这张表数据的 笛卡尔积
        JavaPairRDD<Row, Row> cartesian = userInfoRdd.cartesian(userInfoRdd);
        //计算用户的相似度
        JavaPairRDD<Long, Rating> javaPairRDD = cartesian.mapToPair(row -> {
            Row row1 = row._1();
            Row row2 = row._2();
            Long userId1 = row1.getLong(1);
            Long userId2 = row2.getLong(1);
            Long key = userId1 + userId2 + RandomUtils.nextLong();
            // 自己与自己对比
            if (userId1.longValue() == userId2.longValue()) {
                return new Tuple2<>(key % 10, new Rating(userId1.intValue(), userId2.intValue(), 0));
            }
            double similarity = 0;
            //计算年龄差
            int ageDiff = Math.abs(row1.getInt(6) - row2.getInt(6));
            if (ageDiff <= 2) {
                similarity += 30;
            } else if (ageDiff >= 3 && ageDiff <= 5) {
                similarity += 20;
            } else if (ageDiff > 5 && ageDiff <= 10) {
                similarity += 10;
            }
            // 计算性别
            if (row1.getInt(5) != row2.getInt(5)) {
                similarity += 30;
            }
            // 计算城市
            String city1 = StringUtils.substringBefore(row1.getString(8), "-");
            String city2 = StringUtils.substringBefore(row2.getString(8), "-");
            if (StringUtils.equals(city1, city2)) {
                similarity += 20;
            }
            // 计算学历
            String edu1 = row1.getString(7);
            String edu2 = row2.getString(7);
            if (StringUtils.equals(edu1, edu2)) {
                similarity += 20;
            }
            Rating rating = new Rating(userId1.intValue(), userId2.intValue(), similarity);
            return new Tuple2<>(key % 10, rating);
        });
        //MLlib进行计算最佳的推荐模型
        SparkRecommendModel model = new SparkRecommendModel();
        MatrixFactorizationModel bestModel = model.model(javaPairRDD);
        //将数据写入到MongoDB中
        JavaSparkContext jsc = new JavaSparkContext(sparkSession.sparkContext());
        for (Long userId : userIds) {
            Rating[] ratings = bestModel.recommendProducts(userId.intValue(), 50);
            JavaRDD<Document> documentJavaRDD = jsc.parallelize(Arrays.asList(ratings)).map(v1 -> {
                Document document = new Document();
                document.put("_id", ObjectId.get());
                document.put("frienId", v1.product());
                document.put("userId", v1.user());
                //得分，保留2位小数
                double similarity = new BigDecimal(v1.rating()).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                document.put("similarity", similarity);
                document.put("created", new DateTime().toString("yyyy/MM/dd"));
                return document;
            });
            MongoSpark.save(documentJavaRDD);
        }
    }
}