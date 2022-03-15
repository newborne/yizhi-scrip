package com.yizhi.server.service.v1.impl;

import com.yizhi.common.model.dto.AnnouncementDTO;
import com.yizhi.common.model.dto.CommentMessageDTO;
import com.yizhi.common.model.dto.PageInfoDTO;
import com.yizhi.common.model.pojo.mongodb.Comment;
import com.yizhi.common.model.pojo.mysql.ApAnnouncement;
import com.yizhi.common.model.pojo.mysql.ApUserInfo;
import com.yizhi.common.model.vo.ResponseResult;
import com.yizhi.common.util.RelativeDateFormat;
import com.yizhi.common.util.UserThreadLocal;
import com.yizhi.dubbo.api.v1.CommentApi;
import com.yizhi.server.service.v1.ApAnnouncementService;
import com.yizhi.server.service.v1.ApUserInfoService;
import com.yizhi.server.service.v1.MessageService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Message service.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @DubboReference(version = "1.0.0")
    private CommentApi commentApi;
    @Autowired
    private ApUserInfoService apUserInfoService;
    @Autowired
    private ApAnnouncementService apAnnouncementService;
    @Override
    public ResponseResult queryMessageCommentList(Integer type, Integer page, Integer size) {
        List<CommentMessageDTO> dtos = new ArrayList<>();
        List<Comment> commentList = this.commentApi.queryCommentListByPublishUserId(Long.valueOf(UserThreadLocal.get()
                .getId()), type, page, size);
        for (Comment comment : commentList) {
            CommentMessageDTO dto = new CommentMessageDTO();
            ApUserInfo userInfo = this.apUserInfoService.queryUserInfoByUserId(comment.getUserId());
            dto.setId(comment.getId().toHexString());
            dto.setLogo(userInfo.getLogo());
            dto.setNickName(userInfo.getNickName());
            dto.setCreated(RelativeDateFormat.format(new Date(comment.getCreated())));
            dtos.add(dto);
        }
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, dtos));
    }
    @Override
    public ResponseResult queryMessageAnnouncementList(Integer page, Integer size) {
        List<AnnouncementDTO> dtos = new ArrayList<>();
        List<ApAnnouncement> apAnnouncementList = this.apAnnouncementService.queryAnnouncementList(page, size)
                .getRecords();
        for (ApAnnouncement apAnnouncement : apAnnouncementList) {
            AnnouncementDTO dto = new AnnouncementDTO();
            dto.setId(apAnnouncement.getId().toString());
            dto.setTitle(apAnnouncement.getTitle());
            dto.setDescription(apAnnouncement.getDescription());
            dto.setCreated(RelativeDateFormat.format(Date.from(apAnnouncement.getCreated()
                    .atZone(ZoneId.systemDefault())
                    .toInstant())));
            dtos.add(dto);
        }
        return ResponseResult.ok(new PageInfoDTO<>(0, page, size, dtos));
    }
}
