package com.example.springdemo.demos.web.controller;

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
    public R user(@RequestParam("count") Integer count) throws Exception {
        log.info("user");
        return R.success("dd");
    }

    @GetMapping("/open/something")
    public String something() throws Exception {
        List<CompletableFuture<String>> completableFutures = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            CompletableFuture<String> stringCompletableFuture = asyncService.doSomethingInteger(i);
            completableFutures.add(stringCompletableFuture);
        }

//        CompletableFuture[] array = completableFutures.toArray(new CompletableFuture[0]);
//        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).handle()
//        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).exceptionally((e) -> {
//            log.error("An error occurred: ", e);
//            return null;
//        });

//        最好用get(long timeout,Timeout unit) 设置超时时间，这样不会一直阻塞。
//        try {
//            for (CompletableFuture<?> completableFuture : completableFutures) {
//                completableFuture.get(30, TimeUnit.SECONDS);
//            }
//        } catch (Exception e) {
//            log.error("An error occurred: ", e);
//            completableFutures.stream()
//                    .filter(f -> !f.isDone())
//                    .forEach(f -> f.cancel(true));
//        }
        //thenRun,thenApply,thenAccept,thenAsync  即使这样写allOf还是得等一组内的线程完成才分析thenRun还是exceptionally
//        因为 CompletableFuture的设计并没有其中断或取消一个已经开始
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(completableFutures.toArray(new CompletableFuture[0]));
        objectCompletableFuture.exceptionally((e) -> {
            log.error("An error occurred: ", e);
            for (CompletableFuture<String> completableFuture : completableFutures) {
                if (!completableFuture.isDone()) {
                    completableFuture.complete("new Object()");
                }
            }
            throw new RuntimeException(e);
        });
//        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
        log.info("true end                                                                             ll");
        return "ok";
    }
}
