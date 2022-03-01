package com.yizhi.user.controller.v1;

import com.yizhi.common.model.vo.PicUploadResult;
import com.yizhi.user.service.PicUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/pic")
public class PicUploadController {
    @Autowired
    private PicUploadService picUploadService;

    @PostMapping("upload")
    public PicUploadResult upload(MultipartFile file){
        return this.picUploadService.upload(file);
    }
}
