package com.example.springdemo.demos.web.service;

import com.example.springdemo.demos.web.anno.SonTransaction;
import com.example.springdemo.demos.web.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

    @Autowired
    private SysUserService sysUserService;

    @Async("doSomethingExecutor")
    @SonTransaction
    public CompletableFuture<String> doDb(Integer value, Thread thread) throws Exception {
        log.info("do Integer value: {},thread:{}", value, Thread.currentThread().getName());
        SysUser user = new SysUser();
        user.setId(10L);
        user.setName("theonefx");
        user.setAge(666);
        user.setEmail("123456789@qq.com");
        // 随机数生成
        Integer i = (int) (Math.random() * 10000);
        if (i < 2000) {
            int j = 1 / 0;
        }
        sysUserService.insert(user);
        return CompletableFuture.completedFuture("do something1: " + value);
    }

    @Async("doSomethingExecutor")
    public CompletableFuture<String> doSomethingInteger(int value) throws InterruptedException {
        log.info("do Integer value: {}", value);
        if (value == 2) {
            int j = 1 / 0;
        }
        Thread.sleep(10000);
        log.info("complete: {}", value);
        return CompletableFuture.completedFuture("do something2: " + value);
//        return new AsyncResult<>("doSomethingInteger: " + value);
    }

    @Async("doSomethingExecutor")
    public CompletableFuture<String> doSomething1(String message) throws InterruptedException {
        log.info("do something1: {}", message);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture("do something1: " + message);
    }

    @Async("doSomethingExecutor")
    public CompletableFuture<String> doSomething2(String message) throws InterruptedException {
        log.info("do something2: {}", message);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture("; do something2: " + message);
    }

    @Async("doSomethingExecutor")
    public CompletableFuture<String> doSomething3(String message) throws InterruptedException {
        log.info("do something3: {}", message);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture("; do something3: " + message);
    }
}