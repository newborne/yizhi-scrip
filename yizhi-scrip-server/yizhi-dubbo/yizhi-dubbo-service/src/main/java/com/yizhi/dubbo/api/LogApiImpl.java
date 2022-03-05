package com.yizhi.dubbo.api;

import com.yizhi.common.model.pojo.mongodb.Log;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@DubboService(version = "1.0.0")
public class LogApiImpl implements LogApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Long id, String requestURI, String annotation) {
        Log log = new Log(id, System.currentTimeMillis(), requestURI, annotation);
        this.mongoTemplate.save(log);
    }
}
