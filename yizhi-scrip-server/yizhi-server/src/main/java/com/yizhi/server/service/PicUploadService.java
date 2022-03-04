package com.yizhi.server.service;

import com.yizhi.common.model.vo.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface PicUploadService {

    PicUploadResult upload(MultipartFile uploadFile);
}
