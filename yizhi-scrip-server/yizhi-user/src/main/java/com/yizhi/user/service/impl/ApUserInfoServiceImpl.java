package com.yizhi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yizhi.common.model.enums.SexEnum;
import com.yizhi.common.model.mapper.ApUserInfoMapper;
import com.yizhi.common.model.pojo.ApUser;
import com.yizhi.common.model.pojo.ApUserInfo;
import com.yizhi.common.model.vo.PicUploadResult;
import com.yizhi.user.service.ApUserInfoService;
import com.yizhi.user.service.ApUserService;
import com.yizhi.user.service.FaceEngineService;
import com.yizhi.user.service.PicUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
public class ApUserInfoServiceImpl implements ApUserInfoService {
    @Autowired
    private ApUserService userService;

    @Resource
    private ApUserInfoMapper userInfoMapper;

    @Autowired
    private FaceEngineService faceEngineService;

    @Autowired
    private PicUploadService picUploadService;

    @Override
    public Boolean saveUserInfo(Map<String, String> param, String token) {
        //校验token
        ApUser user = this.userService.queryUserByToken(token);
        if (null == user) {
            return false;
        }
        ApUserInfo userInfo = new ApUserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setSex(StringUtils.equalsIgnoreCase(param.get("sex"), "man") ? SexEnum.MAN : SexEnum.WOMAN);
        userInfo.setNickName(param.get("nickname"));
        userInfo.setBirthday(param.get("birthday"));
        userInfo.setCity(param.get("city"));
        userInfo.setUserId(user.getId());
        return this.userInfoMapper.insert(userInfo) == 1;
    }

    @Override
    public Boolean saveUserLogo(MultipartFile file, String token) {
        //要通过token找到对应的用户
        ApUser user = userService.queryUserByToken(token);
        if (null == user) {
            return false;
        }
        //如果不是人像就返回false
        try {
            boolean b = faceEngineService.checkIsPortrait(file.getBytes());
            if (b) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
        //是人像,上传阿里云
        PicUploadResult upload = picUploadService.upload(file);
        if (StringUtils.equalsIgnoreCase(upload.getStatus(), "done")) {
            return false;
        }
        ApUserInfo userInfo = new ApUserInfo();
        userInfo.setLogo(upload.getName());
        //更新图片地址到userinfo
        LambdaQueryWrapper<ApUserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApUserInfo::getUserId, user.getId());
        return userInfoMapper.update(userInfo, queryWrapper) == 1;
    }
}
