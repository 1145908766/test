package com.omni.mongotest;

import cn.hutool.core.thread.ThreadUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.omni.mongotest.manager.ExecManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest(classes = MongoTestApplication.class)
class MongoTestApplicationTests {

    @Autowired
    private ExecManager execManager;

    @Test
    void loadTest() throws JsonProcessingException {
        execManager.loadTest();
    }

    @Test
    void insertTest() throws JsonProcessingException {
        execManager.insertPolTest();
    }

    @Test
    void insertTest2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            ThreadUtil.execute(() -> {
                execManager.insertTextDoc();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

    }


    @Test
    void insertTest3() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            ThreadUtil.execute(() -> {
                execManager.insertTextDocOne();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
    }

    @Test
    void insertTest4() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            ThreadUtil.execute(() -> {
                execManager.insertTextDocBatch();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
    }
}
