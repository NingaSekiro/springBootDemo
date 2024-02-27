/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springdemo.demos.web.controller;

import com.alibaba.fastjson.JSON;
import com.example.springdemo.demos.web.db.mapper.SysUserMapper;
import com.example.springdemo.demos.web.model.R;
import com.example.springdemo.demos.web.model.SysUser;
import com.example.springdemo.demos.web.model.ValidationResult;
import com.example.springdemo.demos.web.service.AsyncService;
import com.example.springdemo.demos.web.service.SysUserService;
import com.example.springdemo.demos.web.util.ValidateUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
@Slf4j
public class BasicController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SysUserService sysUserService;


    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    public R user() {
        SysUser user = new SysUser();
//        user.setId(10L);
        user.setName("theonefx");
        user.setAge(666);
        user.setEmail("123456789@qq.com");
//        sysUserService.insert(user);
        ValidationResult validationResult = ValidateUtil.validateEntity(user);
        if (validationResult.isHasErrors()) {
            return R.error("validationResult.getMessage()");
        }
        return R.success("dd");
//        return user;
    }

    //     http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    public R saveUser(SysUser u) {
        return R.success("成功");
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, SysUser user) {
        user.setName("zhangsan");
        user.setAge(18);
    }

    @GetMapping("/beanList")
    public String beanList() {
        return JSON.toJSONString(applicationContext.getBeanDefinitionNames());
    }


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

    @SneakyThrows
    @GetMapping("/open/something")
    public void something() {
        List<CompletableFuture<?>> completableFutures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<String> stringCompletableFuture = asyncService.doSomethingInt(i);
//            stringCompletableFuture.exceptionally((e) -> {
//                throw new RuntimeException(e.getMessage());
//            });
            completableFutures.add(stringCompletableFuture);
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
