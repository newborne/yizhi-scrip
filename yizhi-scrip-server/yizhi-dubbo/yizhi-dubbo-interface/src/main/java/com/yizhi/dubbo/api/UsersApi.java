package com.yizhi.dubbo.api;

import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Users;

import java.util.List;

public interface UsersApi {
    String saveUsers(Users users);
    List<Users> queryUsersList(Long userId);
    PageInfoDTO<Users> queryUsersList(Long userId, Integer page, Integer size);
}
