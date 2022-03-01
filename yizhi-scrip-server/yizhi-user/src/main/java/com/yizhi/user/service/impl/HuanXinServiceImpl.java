package com.yizhi.user.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yizhi.common.config.HuanXinConfig;
import com.yizhi.common.model.vo.HuanXinUser;
import com.yizhi.user.service.HuanXinService;
import com.yizhi.user.service.HuanXinTokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class HuanXinServiceImpl implements HuanXinService {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private HuanXinConfig huanXinConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HuanXinTokenService huanXinTokenService;

    @Override
    public boolean register(Long userId) {
        String url = this.huanXinConfig.getUrl()
                + this.huanXinConfig.getOrgName() + "/"
                + this.huanXinConfig.getAppName() + "/users";
        String token = this.huanXinTokenService.getToken();
        //请求头信息
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Bearer " + token);
        List<HuanXinUser> huanXinUsers = new ArrayList<>();
        huanXinUsers.add(new HuanXinUser(userId.toString(), DigestUtils.md5Hex(userId + "_newborne_yizhi")));
        try {
            HttpEntity<String> httpEntity = new HttpEntity(MAPPER.writeValueAsString(huanXinUsers), httpHeaders);
            //发起请求
            ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(url, httpEntity, String.class);
            return responseEntity.getStatusCodeValue() == 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contactUsers(Long userId, Long friendId) {
        String targetUrl = this.huanXinConfig.getUrl()
                + this.huanXinConfig.getOrgName() + "/"
                + this.huanXinConfig.getAppName() + "/users/" +
                userId + "/contacts/users/" + friendId;
        try {
            String token = this.huanXinTokenService.getToken();
            // 请求头
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json ");
            headers.add("Authorization", "Bearer " + token);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(targetUrl, httpEntity, String.class);
            return responseEntity.getStatusCodeValue() == 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 添加失败
        return false;
    }

    @Override
    public Boolean sendMsg(String target, String msg, String type) {
        String targetUrl = this.huanXinConfig.getUrl()
                + this.huanXinConfig.getOrgName() + "/"
                + this.huanXinConfig.getAppName() + "/messages";
        try {
            String token = this.huanXinTokenService.getToken();
            //请求头
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json ");
            headers.add("Authorization", "Bearer " + token);
            Map<String, Object> requestParam = new HashMap<>();
            requestParam.put("target_type", "users");
            requestParam.put("target", Arrays.asList(target));
            Map<String, Object> msgParam = new HashMap<>();
            msgParam.put("msg", msg);
            msgParam.put("type", type);
            requestParam.put("msg", msgParam);
            //表示消息发送者;无此字段Server会默认设置为“from”:“admin”，有from字段但值为空串(“”)时请求失败
//        requestParam.put("from", null);
            HttpEntity<String> httpEntity = new HttpEntity<>(MAPPER.writeValueAsString(requestParam), headers);
            ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(targetUrl, httpEntity, String.class);
            return responseEntity.getStatusCodeValue() == 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
