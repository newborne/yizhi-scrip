package com.yizhi.server.controller.v1;

import com.yizhi.common.model.request.RecommendUserRequest;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.server.service.RecommendUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/recommendUser")
@Slf4j
public class RecommendUserController {

    @Autowired
    private RecommendUserService recommendUserService;

    @GetMapping("todayBest")
    public ResponseResult queryTodayBest() {
        return this.recommendUserService.queryTodayBest();
    }

    @GetMapping("{id}")
    public ResponseResult queryRecommendUser(@PathVariable("id") Long friendId) {
        return this.recommendUserService.queryRecommendUser(friendId);
    }

    @GetMapping("recommendUserList")
    public ResponseResult queryRecommendUserList(RecommendUserRequest param) {
        return this.recommendUserService.queryRecommendUserList(param);
    }
//    @GetMapping("strangerQuestions")
//    public ResponseEntity<String> queryQuestion(@RequestParam("userId") Long userId) {
//        try {
//            String question = this.todayBestService.queryQuestion(userId);
//            return ResponseEntity.ok(question);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }
//
//    @PostMapping("strangerQuestions")
//    public ResponseEntity<Void> replyQuestion(@RequestBody Map<String, Object> param) {
//        Long userId = Long.valueOf(param.get("userId").toString());
//        String reply = param.get("reply").toString();
//        Boolean result = this.todayBestService.replyQuestion(userId, reply);
//        if (result) {
//            return ResponseEntity.ok(null);
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }
//
//    @GetMapping("search")
//    public ResponseEntity<List<NearUserVo>> queryNearUser(@RequestParam(value = "sex", required = false) String gender,
//                                                          @RequestParam(value = "distance", defaultValue = "2000") String distance) {
//        try {
//            List<NearUserVo> list = this.todayBestService.queryNearUser(gender, distance);
//            return ResponseEntity.ok(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }
//
//    @GetMapping("cards")
//    public ResponseEntity<List<TodayBest>> queryCardsList() {
//        try {
//            List<TodayBest> list = this.todayBestService.queryCardsList();
//            return ResponseEntity.ok(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }
//
//    @GetMapping("{id}/love")
//    public ResponseEntity<Void> likeUser(@PathVariable("id") Long likeUserId) {
//        try {
//            this.todayBestService.likeUser(likeUserId);
//            return ResponseEntity.ok(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//
//    @GetMapping("{id}/unlove")
//    public ResponseEntity<Void> disLikeUser(@PathVariable("id") Long likeUserId) {
//        try {
//            this.todayBestService.disLikeUser(likeUserId);
//            return ResponseEntity.ok(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
}

