package com.yizhi.server.monitor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yizhi.common.model.enums.MsgEnum;
import com.yizhi.common.model.pojo.mongodb.Video;
import com.yizhi.common.model.pojo.mongodb.VideoListenerMsg;
import com.yizhi.dubbo.api.VideoApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "YIZHI_VIDEO_TOPIC", consumerGroup = "VIDEO_CONSUMER_GROUP")
public class VideoListener implements RocketMQListener<String> {
    @DubboReference(version = "1.0.0")
    private VideoApi videoApi;
    @Autowired
    private MongoTemplate mongoTemplate;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public void onMessage(String s) {
        try {
            // 1 读取消息
            System.out.println("video:" + s);
            JsonNode jsonNode = MAPPER.readTree(s);
            Long userId = jsonNode.get("userId").asLong();
            MsgEnum type = MsgEnum.values()[jsonNode.get("type").asInt()];
            Long created = jsonNode.get("created").asLong();
            Long videoRid = jsonNode.get("videoRid").asLong();
            // 2 填充数据
            VideoListenerMsg videoListenerMsg = new VideoListenerMsg();
            int rating = 0;
            // 2.1 填充rating
            switch (type) {
                case SAVE:
                    rating = 10;
                    break;
                case LIKE:
                    rating = 10;
                    break;
                case DISLIKE:
                    rating = -10;
                    break;
                case COMMENT:
                    rating = 20;
                    break;
                case QUERY:
                    rating = 5;
                    break;
                default:
                    break;
            }
            videoListenerMsg.setRating((long) rating);
            // 2.2 填充其他字段
            videoListenerMsg.setUserId(userId);
            videoListenerMsg.setCreated(created);
            videoListenerMsg.setVideoRid(videoRid);
            // 3 存储数据
            this.mongoTemplate.save(videoListenerMsg, "video_listener_msg_" + System.currentTimeMillis());
        } catch (JsonProcessingException e) {
            // e.printStackTrace();
            System.out.println("类" + this.getClass().getName() + "中" + Thread.currentThread()
                    .getStackTrace()[1].getMethodName() + "方法：" + "video消息解析失败");
        }
    }
}