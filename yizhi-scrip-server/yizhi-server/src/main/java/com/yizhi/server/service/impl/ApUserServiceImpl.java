package com.yizhi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yizhi.common.model.enums.SexEnum;
import com.yizhi.common.model.mapper.ApUserInfoMapper;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.pojo.mysql.ApUserInfo;
import com.yizhi.common.model.vo.PicUploadResult;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.server.service.ApUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ApUserServiceImpl implements ApUserService {

    @Resource
    private ApUserInfoMapper userInfoMapper;

    @Autowired
    private FaceEngineServiceImpl faceEngineService;

    @Autowired
    private PicUploadServiceImpl picUploadService;

    @Override
    public Boolean saveUserInfo(Map<String, String> param) {
        ApUser user = UserThreadLocal.get();
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
    public Boolean saveUserLogo(MultipartFile file) {
        ApUser user = UserThreadLocal.get();
        //如果不是人像就返回false
        try {
            boolean b = faceEngineService.checkIsPortrait(file.getBytes());
            if (!b) {
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

    @Override
    public ApUserInfo queryUserInfoByUserId(Long id) {
        LambdaQueryWrapper<ApUserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApUserInfo::getUserId, id);
        return userInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public List<ApUserInfo> queryUserInfoList(QueryWrapper queryWrapper) {
        return this.userInfoMapper.selectList(queryWrapper);
    }

    @Override
    public Boolean updateUserInfoByUserId(ApUserInfo userInfo) {
        QueryWrapper<ApUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userInfo.getUserId());
        return this.userInfoMapper.update(userInfo, queryWrapper) > 0;
    }
}
