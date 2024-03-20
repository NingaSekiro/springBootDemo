package com.example.springdemo.demos.web.controller;

import com.example.springdemo.demos.web.anno.MainTransaction;
import com.example.springdemo.demos.web.model.R;
import com.example.springdemo.demos.web.service.AsyncService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @SneakyThrows
    @GetMapping("/open/somethings")
    public String somethings() {
        CompletableFuture<String> createOrder = asyncService.doSomething1("create order");
        CompletableFuture<String> reduceAccount = asyncService.doSomething2("reduce account");
        CompletableFuture<String> saveLog = asyncService.doSomething3("save log");
        // 等待所有任务都执行完
        CompletableFuture.allOf(createOrder, reduceAccount, saveLog).join();
        // 获取每个任务的返回结果
        String result = createOrder.get() + reduceAccount.get() + saveLog.get();
        return result;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @MainTransaction
    public R user(@RequestParam("count") Integer count) throws Exception {
        log.info("user");
        for (Integer i = 0; i < count; i++) {
            asyncService.doSomethingInt(count,Thread.currentThread());
        }
        return R.success("dd");
    }

    @SneakyThrows
    @GetMapping("/open/something")
    public void something() {
        List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
//            CompletableFuture<String> stringCompletableFuture = asyncService.doSomethingInt(i);
//            stringCompletableFuture.exceptionally((e) -> {
//                throw new RuntimeException(e.getMessage());
//            });
//            completableFutures.add(stringCompletableFuture);
        }
//        CompletableFuture.allOf(completableFutures.toArray(futuresArray)).exceptionally((e) -> {
//            log.error("An error occurred: ", e);
//            return null;
//        });
//        completableFutures.forEach((future) -> {
//            try {
//                log.info("result: {}", future.get());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//        });
        //thenRun,thenApply,thenAccept,thenAsync
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).thenRun(() -> {
            log.info("end");
        }).exceptionally((e) -> {
            log.error("An error occurred: ", e);
            return null;
        }).join();
        log.info("true end                                                                             ll");
    }
}
