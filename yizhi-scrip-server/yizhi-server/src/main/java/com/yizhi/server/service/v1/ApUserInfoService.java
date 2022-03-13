package com.yizhi.server.service.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yizhi.common.model.pojo.mysql.ApUserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ApUserInfoService {
    Boolean saveUserInfo(Map<String, String> param);
    Boolean saveUserLogo(MultipartFile file);
    ApUserInfo queryUserInfoByUserId(Long userId);
    List<ApUserInfo> queryUserInfoList(QueryWrapper queryWrapper);
    Boolean updateUserInfo(ApUserInfo userInfo);
}
