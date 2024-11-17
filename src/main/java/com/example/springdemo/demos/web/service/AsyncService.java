package com.example.springdemo.demos.web.service;

import com.example.springdemo.demos.web.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

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

    public static void main(String[] args) {
        HashMap<Order, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            Order order=new Order();
            order.setName("ddd"+i);
            hashMap.put(order, i);
        }
        System.out.println(hashMap.size());
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
class Student {
    private int age;

    public Student(int age) {
        this.age = age;
    }

    //重写hashCode 为了使所有的值挂在一个链表上
    @Override
    public int hashCode() {
        return age;
    }
}