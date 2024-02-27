package com.example.springdemo.demos.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@Service
public class AsyncService {
    @Async("doSomethingExecutor")
    public CompletableFuture<String> doSomethingInt(Integer value) throws Exception {
        log.info("do Integer value: {},thread:{}", value, Thread.currentThread().getName());
        Thread.sleep(1000);
        if (value == 50) {
            throw new Exception("error message");
        }
        return CompletableFuture.completedFuture("do something1: " + value);
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