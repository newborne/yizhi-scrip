package com.yizhi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.dto.UserInfoDTO;
import com.yizhi.common.model.dto.UsersDTO;
import com.yizhi.common.model.enums.SexEnum;
import com.yizhi.common.model.pojo.mongodb.TimeLine;
import com.yizhi.common.model.pojo.mongodb.Users;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.pojo.mysql.ApUserInfo;
import com.yizhi.common.model.vo.PicUploadResult;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.UsersApi;
import com.yizhi.server.service.ApUserInfoService;
import com.yizhi.server.service.HuanXinService;
import com.yizhi.server.service.PicUploadService;
import com.yizhi.server.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private PicUploadService picUploadService;
    @Autowired
    private HuanXinService huanXinService;
    @Autowired
    private ApUserInfoService apUserInfoService;
    @DubboReference(version = "1.0.0")
    private UsersApi usersApi;
    @Override
    public ResponseResult user() {
        return this.huanXinService.user();
    }
    @Override
    public ResponseResult register(Long userId) {
        return this.huanXinService.register(userId);
    }
    @Override
    public ResponseResult sendMsg(String target, String msg, String type) {
        return this.huanXinService.sendMsg(target, msg, type);
    }
    @Override
    public ResponseResult saveUserInfo(Map<String, String> param) {
        return (this.apUserInfoService.saveUserInfo(param)) ? ResponseResult.ok() : ResponseResult.fail();
    }
    @Override
    public ResponseResult saveUserLogo(MultipartFile file) {
        return (this.apUserInfoService.saveUserLogo(file)) ? ResponseResult.ok() : ResponseResult.fail();
    }
    @Override
    public ResponseResult queryUserInfo(Long userId) {
        ApUserInfo apUserInfo = this.apUserInfoService.queryUserInfoByUserId(userId);
        UserInfoDTO dto = new UserInfoDTO();
        dto.setId(Long.valueOf(apUserInfo.getId()));
        dto.setLogo(apUserInfo.getLogo());
        dto.setNickName(apUserInfo.getNickName());
        dto.setBirthday(apUserInfo.getBirthday());
        dto.setAge(apUserInfo.getAge().toString());
        dto.setSex(apUserInfo.getSex().name().toLowerCase());
        dto.setCity(apUserInfo.getCity());
        dto.setEdu(apUserInfo.getEdu());
        dto.setIndustry(apUserInfo.getIndustry());
        return ResponseResult.ok(dto);
    }
    @Override
    public ResponseResult updateUserInfo(Map<String, String> param) {
        ApUser user = UserThreadLocal.get();
        ApUserInfo apserInfo = new ApUserInfo();
        apserInfo.setUserId(user.getId());
        apserInfo.setNickName(param.get("nickName"));
        apserInfo.setSex(StringUtils.equalsIgnoreCase(param.get("sex"), "man") ? SexEnum.MAN : SexEnum.WOMAN);
        apserInfo.setAge(Integer.valueOf(param.get("age")));
        apserInfo.setEdu(param.get("edu"));
        apserInfo.setCity(param.get("city"));
        apserInfo.setBirthday(param.get("birthday"));
        apserInfo.setIndustry(param.get("industry"));
        return (this.apUserInfoService.updateUserInfo(apserInfo)) ? ResponseResult.ok() : ResponseResult.fail();
    }
    @Override
    public ResponseResult addUsers(Long friendId) {
        ApUser user = UserThreadLocal.get();
        Users users = new Users();
        users.setUserId(Long.valueOf(user.getId()));
        users.setFriendId(friendId);
        // mongodb
        if (StringUtils.isNotEmpty(this.usersApi.saveUsers(users))) {
            return this.huanXinService.contactUsers(Long.valueOf(user.getId()), friendId);
        }
        return ResponseResult.fail();
    }
    @Override
    public ResponseResult queryUsersList(Integer page, Integer size, String keyword) {
        ApUser user = UserThreadLocal.get();
        List<UsersDTO> dtos = new ArrayList<>();
        if (StringUtils.isEmpty(keyword)) {
            List<Users> usersList = this.usersApi.queryUsersList(Long.valueOf(user.getId()), page, size);
            for (Users users : usersList) {
                // 填充UserInfo
                ApUserInfo userInfo = this.apUserInfoService.queryUserInfoByUserId(users.getFriendId());
                UsersDTO dto = new UsersDTO();
                dto.setUserId(String.valueOf(users.getFriendId()));
                dto.setLogo(userInfo.getLogo());
                dto.setNickName(userInfo.getNickName());
                dto.setSex(userInfo.getSex().name().toLowerCase());
                dto.setAge(Integer.valueOf(userInfo.getAge().toString()));
                dto.setCity(StringUtils.substringBefore(userInfo.getCity(), "-"));
                dtos.add(dto);
            }
        } else {
            List<Users> usersList = this.usersApi.queryUsersList(Long.valueOf(user.getId()));
            List<Long> userIds = usersList.stream().map(Users::getFriendId).collect(Collectors.toList());
            QueryWrapper<ApUserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("nick_name", keyword).in("user_id", userIds);
            List<ApUserInfo> userInfoList = this.apUserInfoService.queryUserInfoList(queryWrapper);
            for (ApUserInfo userInfo : userInfoList) {
                UsersDTO dto = new UsersDTO();
                dto.setUserId(String.valueOf(userInfo.getUserId()));
                dto.setLogo(userInfo.getLogo());
                dto.setNickName(userInfo.getNickName());
                dto.setSex(userInfo.getSex().name().toLowerCase());
                dto.setAge(Integer.valueOf(userInfo.getAge().toString()));
                dto.setCity(StringUtils.substringBefore(userInfo.getCity(), "-"));
                dtos.add(dto);
            }
            // 未分页
            page = 0;
            size = 0;
        }
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, dtos));
    }
}
