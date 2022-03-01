package com.yizhi.user.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ApUserInfoService {

    Boolean saveUserInfo(Map<String, String> param, String token);

    Boolean saveUserLogo(MultipartFile file, String token);
}
