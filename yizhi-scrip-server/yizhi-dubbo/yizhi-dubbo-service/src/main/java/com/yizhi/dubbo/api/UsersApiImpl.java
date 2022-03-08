package com.yizhi.dubbo.api;

import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Users;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@DubboService(version = "1.0.0")
public class UsersApiImpl implements UsersApi {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public String saveUsers(Users users) {
        //判断好友关系是否存在
        Query query = Query.query(Criteria.where("userId")
                .is(users.getUserId())
                .and("friendId")
                .is(users.getFriendId()));
        if (this.mongoTemplate.count(query, Users.class) == 0) {
            users.setId(ObjectId.get());
            users.setCreated(System.currentTimeMillis());
            //不存在则建立好友关系
            this.mongoTemplate.save(users);
            return users.getId().toHexString();
        }
        return null;
    }
    @Override
    public List<Users> queryUsersList(Long userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return this.mongoTemplate.find(query, Users.class);
    }
    @Override
    public PageInfoDTO<Users> queryUsersList(Long userId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc("created")));
        Query query = Query.query(Criteria.where("userId").is(userId)).with(pageRequest);
        List<Users> usersList = this.mongoTemplate.find(query, Users.class);
        PageInfoDTO<Users> dto = new PageInfoDTO<>();
        dto.setPage(page);
        dto.setSize(size);
        dto.setRecords(usersList);
        dto.setTotal(0); //不提供总数
        return dto;
    }
}
