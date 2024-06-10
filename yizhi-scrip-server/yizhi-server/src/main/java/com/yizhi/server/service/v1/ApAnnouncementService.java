package com.yizhi.server.service.v1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yizhi.common.model.pojo.mysql.ApAnnouncement;

/**
 * The interface Ap announcement service.
 */
public interface ApAnnouncementService {
    /**
     * Query announcement list page.
     *
     * @param page the page
     * @param size the size
     * @return the page
     */
    IPage<ApAnnouncement> queryAnnouncementList(Integer page, Integer size);
}
