package com.yizhi.server.service.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yizhi.common.model.pojo.mysql.ApAnnouncement;

public interface ApAnnouncementService {
    IPage<ApAnnouncement> queryAnnouncementList(Integer page, Integer size);
}
