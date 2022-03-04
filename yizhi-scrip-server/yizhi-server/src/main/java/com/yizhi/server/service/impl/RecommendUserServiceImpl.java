package com.yizhi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.dto.RecommendUserDTO;
import com.yizhi.common.model.pojo.mongodb.RecommendUser;
import com.yizhi.common.model.pojo.mysql.ApUser;
import com.yizhi.common.model.pojo.mysql.ApUserInfo;
import com.yizhi.common.model.request.RecommendUserRequest;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.RecommendUserApi;
import com.yizhi.server.service.ApUserService;
import com.yizhi.server.service.RecommendUserService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class RecommendUserServiceImpl implements RecommendUserService {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private ApUserService apUserService;
    @DubboReference(version = "1.0.0")
    private RecommendUserApi recommendUserApi;
    private Long defaultRecommendUser = Long.valueOf("2");
    //    @Autowired
//    private RestTemplate restTemplate;
//
//    @Reference(version = "1.0.0")
//    private UserLocationApi userLocationApi;
//
//    @Reference(version = "1.0.0")
//    private UserLikeApi userLikeApi;
//
//    @Autowired
//    private QuestionService questionService;
//
//    @Autowired
//    private IMService imService;
    private String defaultRecommendUsers = "2,3,4,5";

    @Override
    public ResponseResult queryTodayBest() {
        // 校验token是否有效,通过user的接口进行校验
        ApUser user = UserThreadLocal.get();
        RecommendUserDTO dto = new RecommendUserDTO();
        // 通过用户id寻找最佳推荐用户
        RecommendUser recommendUser = this.recommendUserApi.queryWithMaxSimilarity(Long.valueOf(user.getId()));
        if (null == recommendUser) {
            // 给出默认的推荐用户 在配置文件中有设置默认用户直接注入
            // 获取推荐用户个人信息
            ApUserInfo userInfo = this.apUserService.queryUserInfoByUserId(defaultRecommendUser);
            // 补全个人信息
            dto.setFriendId(defaultRecommendUser);
            dto.setAvatar(userInfo.getLogo());
            dto.setNickname(userInfo.getNickName());
            dto.setGender(userInfo.getSex().getValue() == 1 ? "man" : "woman");
            dto.setAge(userInfo.getAge());
            dto.setTags(StringUtils.split(userInfo.getTags(), ","));
            dto.setSimilarity(49L);
        } else {
            // 获取推荐用户个人信息
            ApUserInfo userInfo = this.apUserService.queryUserInfoByUserId(recommendUser.getFriendId());
            // 补全个人信息
            dto.setFriendId(recommendUser.getFriendId());
            dto.setAvatar(userInfo.getLogo());
            dto.setNickname(userInfo.getNickName());
            dto.setGender(userInfo.getSex().getValue() == 1 ? "man" : "woman");
            dto.setAge(userInfo.getAge());
            dto.setTags(StringUtils.split(userInfo.getTags(), ","));
            // 获得相似度 取整
            double similarity = Math.floor(recommendUser.getSimilarity());
            dto.setSimilarity((long) similarity);
        }
        return ResponseResult.ok(dto);
    }

    @Override
    public ResponseResult queryRecommendUser(Long friendId) {
        ApUser user = UserThreadLocal.get();
        ApUserInfo userInfo = this.apUserService.queryUserInfoByUserId(friendId);
        RecommendUserDTO dto = new RecommendUserDTO();
        dto.setFriendId(friendId);
        dto.setAvatar(userInfo.getLogo());
        dto.setNickname(userInfo.getNickName());
        dto.setGender(userInfo.getSex().name().toLowerCase(Locale.ROOT));
        dto.setAge(userInfo.getAge());
        dto.setTags(StringUtils.split(userInfo.getTags(), ","));
        double similarity = this.recommendUserApi.querySimilarity(friendId, Long.valueOf(user.getId()));
        if (similarity == 0) {
            similarity = 90;
        }
        dto.setSimilarity((long) Math.floor(similarity));
        return ResponseResult.ok(dto);
    }

    @Override
    public ResponseResult queryRecommendUserList(RecommendUserRequest request) {
        //获得用户对象
        ApUser user = UserThreadLocal.get();
        PageInfoDTO dto = new PageInfoDTO();
        dto.setPage(request.getPage());
        dto.setSize(request.getSize());
        List<RecommendUser> records = this.recommendUserApi.queryRecommendUserList(Long.valueOf(user.getId()), request.getPage(), request.getSize());
        //判断集合中是否有数据
        if (CollectionUtils.isEmpty(records)) {
            //进行切割
            String[] friendIdsStr = StringUtils.split(defaultRecommendUsers, ",");
            for (String s : friendIdsStr) {
                RecommendUser recommendUser = new RecommendUser();
                recommendUser.setFriendId(Long.valueOf(s));
                recommendUser.setId(ObjectId.get());
                recommendUser.setSimilarity(RandomUtils.nextDouble(70d, 90d));
                records.add(recommendUser);
            }
        }
        //收集推荐用户的id
        Set<Long> friendIds = new HashSet<>();
        for (RecommendUser record : records) {
            friendIds.add(record.getFriendId());
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        //用户的id参数
        queryWrapper.in("user_id", friendIds);

        /*//查询城市参数
        if (StringUtils.isNotEmpty(param.getCity())) {
            queryWrapper.like("city", param.getCity());
        }

        //查询性别参数
        if (StringUtils.isNotEmpty(param.getGender())) {
            queryWrapper.eq("sex", StringUtils.equals(param.getGender(), "man") ? 1 : 2);
        }

        //查询年龄参数
        if (param.getAge() != null) {
            queryWrapper.le("age", param.getAge());
        }*/
        //
        List<ApUserInfo> userInfoList = this.apUserService.queryUserInfoList(queryWrapper);
        if (CollectionUtils.isEmpty(userInfoList)) {
            //没有查到用户基本信息
            return ResponseResult.fail(dto);
        }
        List<RecommendUserDTO> dtos = new ArrayList<>();
        for (ApUserInfo userInfo : userInfoList) {
            RecommendUserDTO dto2 = new RecommendUserDTO();
            //补全个人信息
            dto2.setFriendId(Long.valueOf(userInfo.getUserId()));
            dto2.setAvatar(userInfo.getLogo());
            dto2.setAge(userInfo.getAge());
            dto2.setNickname(userInfo.getNickName());
            dto2.setGender(userInfo.getSex().getValue() == 1 ? "man" : "woman");
            dto2.setTags(StringUtils.split(userInfo.getTags(), ","));
            //设置相似度
            for (RecommendUser record : records) {
                if (record.getFriendId().longValue() == userInfo.getUserId().longValue()) {
                    double similarity = Math.floor(record.getSimilarity());
                    dto2.setSimilarity(Double.valueOf(similarity).longValue());
                    break;
                }
            }
            dtos.add(dto2);
        }
        //相似度倒序排列
        Collections.sort(dtos, (o1, o2) -> new Long(o2.getSimilarity() - o1.getSimilarity()).intValue());
        dto.setRecords(dtos);
        return ResponseResult.ok(dto);
    }
//    public String queryQuestion(Long userId) {
//        ApQuestion question = this.questionService.queryQuestion(userId);
//        if (question != null) {
//            return question.getTxt();
//        }
//        return "";
//    }
//
//    public Boolean replyQuestion(Long userId, String reply) {
//        ApUser user = UserThreadLocal.get();
//        ApUserInfo userInfo = this.apUserService.queryUserInfoByUserId(user.getId());
//        //构建消息内容
//        Map<String, Object> msg = new HashMap<>();
//        msg.put("usrId", user.getId().toString());
//        msg.put("nickname", this.queryQuestion(userId));
//        msg.put("strangerQuestion", userInfo.getNickName());
//        msg.put("reply", reply);
//        try {
//            String msgStr = MAPPER.writeValueAsString(msg);
//            String targetUrl = this.url + "/user/huanxin/messages";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//            params.add("target", userId.toString());
//            params.add("msg", msgStr);
//
//            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
//            ResponseEntity<Void> responseEntity = this.restTemplate.postForEntity(targetUrl, httpEntity, Void.class);
//
//            return responseEntity.getStatusCodeValue() == 200;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    public List<NearUserVo> queryNearUser(String gender, String distance) {
//        ApUser user = UserThreadLocal.get();
//        //查询当前用户位置
//        UserLocationVo userLocationVo = this.userLocationApi.queryByUserId(user.getId());
//        Double longitude = userLocationVo.getLongitude();
//        Double latitude = userLocationVo.getLatitude();
//
//        //根据当前信息搜索附近好友
//        List<UserLocationVo> userLocationVoList = this.userLocationApi.queryUserFromLocation(longitude, latitude, Integer.valueOf(distance));
//        if (CollectionUtils.isEmpty(userLocationVoList)) {
//            return Collections.emptyList();
//        }
//
//        //获取附近的id
//        List<Long> userIds = new ArrayList<>();
//        for (UserLocationVo locationVo : userLocationVoList) {
//            userIds.add(locationVo.getUserId());
//        }
//
//        //查询
//        QueryWrapper<ApUserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("user_id", userIds);
//        //查询性别
//        if (StringUtils.equalsIgnoreCase(gender, "man")) {
//            queryWrapper.eq("sex", SexEnum.MAN);
//        } else {
//            queryWrapper.eq("sex", SexEnum.WOMAN);
//        }
//
//        List<ApUserInfo> userInfoList = this.apUserService.queryUserInfoList(queryWrapper);
//        List<NearUserVo> nearUserVoList = new ArrayList<>();
//        for (UserLocationVo locationVo : userLocationVoList) {
//            //排除自己
//            if (locationVo.getUserId().longValue() == user.getId().longValue()) {
//                continue;
//            }
//
//            for (ApUserInfo userInfo : userInfoList) {
//                if (locationVo.getUserId().longValue() == userInfo.getUserId().longValue()) {
//                    NearUserVo nearUserVo = new NearUserVo();
//                    nearUserVo.setUserId(userInfo.getUserId());
//                    nearUserVo.setAvatar(userInfo.getLogo());
//                    nearUserVo.setNickname(userInfo.getNickName());
//                    nearUserVoList.add(nearUserVo);
//                    break;
//                }
//            }
//        }
//        return nearUserVoList;
//    }
//
//    public List<TodayBest> queryCardsList() {
//        ApUser user = UserThreadLocal.get();
//        PageInfo<RecommendUser> pageInfo = this.recommendUserService.queryRecommendUserList(user.getId(), 1, 50);
//        if (CollectionUtils.isEmpty(pageInfo.getRecords())) {
//            //如果查询为空则使用默认的推荐
//            String[] split = StringUtils.split(defaultRecommendUsers, ",");
//            for (String s : split) {
//                RecommendUser recommendUser = new RecommendUser();
//                recommendUser.setFriendId(Long.valueOf(s));
//                recommendUser.setUserId(Long.valueOf(user.getId()));
//                pageInfo.getRecords().add(recommendUser);
//            }
//        }
//
//        List<RecommendUser> records = pageInfo.getRecords();
//        int showCount = Math.min(10, records.size());
//        //打散集合
//        Collections.shuffle(records);
//
//        List<RecommendUser> newRecommendUserList = records.subList(0, showCount);
//
//        //创建所有的ids集合
//        List<Long> userIds = new ArrayList<>();
//
//        for (RecommendUser recommendUser : newRecommendUserList) {
//            userIds.add(recommendUser.getUserId());
//        }
//
//        QueryWrapper<ApUserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("user_id", userIds);
//        List<ApUserInfo> userInfoList = this.apUserService.queryUserInfoList(queryWrapper);
//        List<TodayBest> todayBestList = new ArrayList<>();
//        for (ApUserInfo userInfo : userInfoList) {
//            TodayBest dto = new TodayBest();
//            dto.setId(Long.valueOf(userInfo.getUserId()));
//            dto.setAge(userInfo.getAge());
//            dto.setAvatar(userInfo.getLogo());
//            dto.setGender(userInfo.getSex().name().toLowerCase());
//            dto.setNickname(userInfo.getNickName());
//            dto.setTags(StringUtils.split(userInfo.getTags(), ','));
//            dto.setSimilarity(0L);
//
//            todayBestList.add(dto);
//        }
//        return todayBestList;
//    }
//
//    public Boolean likeUser(Long likeUserId) {
//        ApUser user = UserThreadLocal.get();
//        String id = this.userLikeApi.saveUserLike(user.getId(), likeUserId);
//        //判断id是否为空
//        if (StringUtils.isEmpty(id)) {
//            return false;
//        }
//        if (this.userLikeApi.isMutualLike(user.getId(), likeUserId)) {
//            //相互喜欢成为好友
//            this.imService.contactUser(likeUserId);
//        }
//
//        return true;
//
//    }
//
//    public Boolean disLikeUser(Long likeUserId) {
//        ApUser user = UserThreadLocal.get();
//        return this.userLikeApi.deleteUserLike(user.getId(),likeUserId);
//    }
}

