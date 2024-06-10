package com.yizhi.server.service.v1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * The type Face engine service test.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FaceEngineServiceTest {
    @Autowired
    private FaceEngineService faceEngineService;
    /**
     * Check is portrait.
     */
    @Test
    public void checkIsPortrait() {
        File file = new File(
                "G:\\newborne\\Src\\Doing\\GitProjects\\newborne\\Individuals\\yizhi-scrip\\yizhi-scrip-server\\Doc\\images\\user_logo.jpg");
        boolean checkIsPortrait = this.faceEngineService.checkIsPortrait(file);
        System.out.println(checkIsPortrait); // true|false
    }
}