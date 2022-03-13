package com.yizhi.server.service.v1;

import com.arcsoft.face.toolkit.ImageInfo;

import java.io.File;

public interface FaceEngineService {
    Boolean checkIsPortrait(ImageInfo imageInfo);
    Boolean checkIsPortrait(byte[] imageData);
    Boolean checkIsPortrait(File file);
}

