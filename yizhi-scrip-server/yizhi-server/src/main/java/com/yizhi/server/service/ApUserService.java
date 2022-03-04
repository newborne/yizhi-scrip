package com.yizhi.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yizhi.common.model.pojo.mysql.ApUserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ApUserService {

    Boolean saveUserInfo(Map<String, String> param);

    Boolean saveUserLogo(MultipartFile file);

    ApUserInfo queryUserInfoByUserId(Long id);

    List<ApUserInfo> queryUserInfoList(QueryWrapper queryWrapper);

    Boolean updateUserInfoByUserId(ApUserInfo userInfo);
}
