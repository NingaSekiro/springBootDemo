package com.example.springdemo.demos.web.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async("myTaskExecutor")
    public void executeAsyncOperation() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("Async operation executing in thread: " + currentThreadName);
    }
}
