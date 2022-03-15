package com.yizhi.temp;

import com.alibaba.cloud.commons.io.FileUtils;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * The type Test fast dfs.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFastDFS {
    /**
     * The Storage client.
     */
    @Autowired
    protected FastFileStorageClient storageClient;
    /**
     * Test upload.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testUpload() throws IOException {
        File file = new File(
                "G:\\newborne\\Src\\Doing\\GitProjects\\newborne\\Individuals\\yizhi-scrip\\yizhi-scrip-server\\Doc\\images\\user_logo.jpg");
        StorePath storePath = this.storageClient.uploadFile(FileUtils.openInputStream(file),
                file.length(),
                "jpg",
                null);
        System.out.println(storePath);
        System.out.println(storePath.getFullPath());
        // StorePath [group=group1, path=M00/00/00/wKhvgGInOgeAfHCVAAITMolCrAg718.jpg]
        // group1/M00/00/00/wKhvgGInOgeAfHCVAAITMolCrAg718.jpg
    }
}
