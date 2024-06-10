package com.yizhi.dubbo.api.v1;

import com.yizhi.common.model.pojo.mongodb.Post;
import com.yizhi.common.model.pojo.mongodb.TimeLine;
import com.yizhi.common.model.pojo.mongodb.Users;
import com.yizhi.common.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Post api.
 */
@DubboService(version = "1.0.0")
@Slf4j
public class PostApiImpl implements PostApi {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public String savePost(Post post) {
        //校验
        if (post.getUserId() != null) {
            //设置创建时间
            post.setCreated(System.currentTimeMillis());
            post.setSeeType(1);
            //设置id
            post.setId(ObjectId.get());
            //增加自增长id
            post.setPostRid(this.idGenerator.createId("post", post.getId().toHexString()));
            //保存发布
            this.mongoTemplate.save(post);
            //写入好友的时间线中
            Criteria criteria = Criteria.where("userId").is(post.getUserId());
            List<Users> users = this.mongoTemplate.find(Query.query(criteria), Users.class);
            for (Users user : users) {
                //构建时间线对象
                TimeLine timeLine = new TimeLine();
                timeLine.setPublishId(post.getId());
                timeLine.setCreated(System.currentTimeMillis());
                timeLine.setUserId(user.getUserId());
                timeLine.setId(ObjectId.get());
                //保存
                this.mongoTemplate.save(timeLine, "post_timeline_" + user.getFriendId());
            }
            return post.getId().toHexString();
        }
        return null;
    }
    @Override
    public Post queryPostById(String id) {
        return this.mongoTemplate.findById(new ObjectId(id), Post.class);
    }
    @Override
    public List<Post> queryFriendPostList(Long userId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc("created")));
        Query query = new Query().with(pageRequest);
        List<TimeLine> timeLines = this.mongoTemplate.find(query,
                TimeLine.class,
                "post_timeline_" + userId);
        List<ObjectId> postIds = timeLines.stream().map(TimeLine::getPublishId).collect(Collectors.toList());
        Query query2 = Query.query(Criteria.where("id").in(postIds)).with(Sort.by(Sort.Order.desc("created")));
        return this.mongoTemplate.find(query2, Post.class);
    }
    @Override
    public List<Post> queryUserPostList(Long userId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc("created")));
        Query query = Query.query(Criteria.where("userId").is(userId))
                .with(Sort.by(Sort.Order.desc("created")))
                .with(pageRequest);
        return this.mongoTemplate.find(query, Post.class);
    }
    @Override
    public List<Post> queryRecommendPostList(Long userId, Integer page, Integer size) {
        // redis命中Rid,mongodb查Post
        String key = "RECOMMEND_POST_" + userId;
        String value = this.redisTemplate.opsForValue().get(key);
        List<Post> postList = null;
        if (StringUtils.isNotEmpty(value)) {
            //命中了数据
            String[] postRids = StringUtils.split(value, ",");
            int startIndex = (page - 1) * size;
            if (startIndex < postRids.length) {
                int endIndex = startIndex + size - 1;
                if (endIndex >= postRids.length) {
                    endIndex = postRids.length - 1;
                }
                List<Long> postRidList = new ArrayList<>();
                for (int i = startIndex; i <= endIndex; i++) {
                    postRidList.add(Long.valueOf(postRids[i]));
                }
                Query query = Query.query(Criteria.where("postRid").in(postRidList));
                postList = this.mongoTemplate.find(query, Post.class);
            }
        }
        return postList;
    }
}
