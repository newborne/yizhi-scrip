package com.yizhi.login.service;

import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface LoginService {

    ResponseResult login(String phone, String code);

    ApUser queryUserByToken(String token);

    ResponseResult saveUserInfo(Map<String, String> param, String token);

    ResponseResult saveUserLogo(MultipartFile file, String token);
}
