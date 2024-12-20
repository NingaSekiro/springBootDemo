package com.example.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class DelayedQueueService {

    @Autowired
    private RDelayedQueue<String> delayedQueue;

    @Autowired
    private RBlockingQueue<String> blockingQueue;


    @PostConstruct
    public void init() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            while (true) {
                try {
                    String task = blockingQueue.take(); // 使用 poll 方法
                    if (task != null) {
                        log.info("receive delay task:{}", task);
                        int i = 1 / 0;
                    }
                } catch (Exception e) {
                    offerTask("ddddd", 30);
                    log.error("occur error", e);
                }
            }
        });
    }

    public void offerTask(String task, long seconds) {
        log.info("add delay task:{},delay time:{}s", task, seconds);
        delayedQueue.offer(task, seconds, TimeUnit.SECONDS);
    }
}