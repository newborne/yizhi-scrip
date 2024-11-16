package com.yizhi.server.service.v1.impl;

import com.yizhi.common.model.enums.MsgEnum;
import com.yizhi.common.model.pojo.mongodb.Article;
import com.yizhi.common.model.pojo.mongodb.Material;
import com.yizhi.common.model.pojo.mongodb.Post;
import com.yizhi.common.model.pojo.mongodb.Video;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.v1.ArticleApi;
import com.yizhi.dubbo.api.v1.MaterialApi;
import com.yizhi.dubbo.api.v1.PostApi;
import com.yizhi.dubbo.api.v1.VideoApi;
import com.yizhi.server.service.v1.MqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Mq service.
 */
@Service
@Slf4j
public class MqServiceImpl implements MqService {
    @DubboReference(version = "1.0.0")
    private PostApi postApi;
    @DubboReference(version = "1.0.0")
    private VideoApi videoApi;
    @DubboReference(version = "1.0.0")
    private ArticleApi articleApi;
    @DubboReference(version = "1.0.0")
    private MaterialApi materialApi;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Override
    public Boolean sendMsg(String destination, MsgEnum type, String publishId) {
        ApUser user = UserThreadLocal.get();
        //构建消息对象
        Map<String, Object> msg = new HashMap<>();
        switch (destination) {
            case "post":
                Post post = this.postApi.queryPostById(publishId);
                msg.put("postRid", post.getPostRid());
                break;
            case "video":
                Video video = this.videoApi.queryVideoById(publishId);
                msg.put("videoRid", video.getVideoRid());
                break;
            case "article":
                Article article = this.articleApi.queryArticleById(publishId);
                msg.put("articleRid", article.getArticleRid());
                break;
            case "material":
                Material material = this.materialApi.queryMaterialById(publishId);
                msg.put("materialRid", material.getMaterialRid());
                break;
            default:
                break;
        }
        msg.put("userId", user.getId());
        msg.put("type", type.getValue());
        msg.put("publishId", publishId);
        msg.put("created", System.currentTimeMillis());
        try {
            this.rocketMQTemplate.convertAndSend("YIZHI_" + destination.toUpperCase() + "_TOPIC", msg);
            return true;
        } catch (Exception e) {
            log.error("发送消息失败!", e);
        }
        return false;
    }
}
