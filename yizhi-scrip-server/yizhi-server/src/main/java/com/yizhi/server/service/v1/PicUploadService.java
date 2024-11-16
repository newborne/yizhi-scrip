package com.yizhi.server.service.v1;

import com.yizhi.common.model.vo.PicUploadResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * The interface Pic upload service.
 */
public interface PicUploadService {
    /**
     * Upload pic pic upload result.
     *
     * @param uploadFile the upload file
     * @return the pic upload result
     */
    PicUploadResult uploadPic(MultipartFile uploadFile);
}
