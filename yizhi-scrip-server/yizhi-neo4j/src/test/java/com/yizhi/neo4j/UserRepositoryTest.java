package com.yizhi.neo4j;

import com.yizhi.common.model.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The type User repository test.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    /**
     * Find all.
     */
    @Test
    public void findAll() {
        System.out.println("类" + this.getClass().getName() + "中" + Thread.currentThread()
                .getStackTrace()[1].getMethodName() + "方法：" + userRepository.findByUserId(4));
    }
}