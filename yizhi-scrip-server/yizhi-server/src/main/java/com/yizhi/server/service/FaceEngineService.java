package com.yizhi.server.service;

import com.arcsoft.face.toolkit.ImageInfo;

import java.io.File;

public interface FaceEngineService {

    boolean checkIsPortrait(ImageInfo imageInfo);

    boolean checkIsPortrait(byte[] imageData);

    boolean checkIsPortrait(File file);
}

