package com.yizhi.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yizhi.common.model.mapper.ApAnnouncementMapper;
import com.yizhi.common.model.pojo.mysql.ApAnnouncement;
import com.yizhi.server.service.ApAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApAnnouncementServiceImpl implements ApAnnouncementService {
    @Resource
    private ApAnnouncementMapper apAnnouncementMapper;
    @Override
    public IPage<ApAnnouncement> queryAnnouncementList(Integer page, Integer size) {
        return this.apAnnouncementMapper.selectPage(new Page<ApAnnouncement>(page, size),
                new QueryWrapper<ApAnnouncement>().orderByDesc("created"));
    }
}
