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
import com.example.springdemo.DelayedQueueService;
import com.example.springdemo.demos.web.anno.OperationLog;
import com.example.springdemo.demos.web.model.R;
import com.example.springdemo.demos.web.model.SysUser;
import com.example.springdemo.demos.web.service.ComputeNodeTaskProcessor;
import com.example.springdemo.demos.web.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BasicController {
    private final ApplicationContext applicationContext;
    private final SysUserService sysUserService;

    private final ComputeNodeTaskProcessor computeNodeTaskProcessor;

    private static final int BUFFER_SIZE = 1024 * 1024; // 1MB
    private static List<ByteBuffer> buffers = new ArrayList<>();


    private final DelayedQueueService redissonDelayQueue;



    @GetMapping("/sendMessage")
    public String getToken() {
        redissonDelayQueue.offerTask("hello,world",30);
        return "ok";
    }

//     http://127.0.0.1:8080/hello?name=lisi
//    @RequestMapping("/hello")
//    public String hello() throws InterruptedException {
//        Thread.sleep(3000);
//        return Thread.currentThread().getName();
//    }

    @OperationLog(paramNames = {"s"})
    @GetMapping("/hello")
    public String hello(String s) throws Exception {
        try {
            while (true) {
                // 不断创建DirectByteBuffer但不释放
                ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
                byte[] fillData = new byte[BUFFER_SIZE];
                java.util.Arrays.fill(fillData, (byte)'d');
                buffer.put(fillData);
                buffers.add(buffer);

                log.info("Allocated " + buffers.size() + "MB direct memory");
                Thread.sleep(70); // 延迟，便于观察
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "dd";
    }

    //     http://127.0.0.1:8080/save_user?name=newName&age=11
    @PostMapping("/save_user")
    @OperationLog(paramNames = {"u.id", "u.name"})
    public R saveUser(@RequestBody SysUser u) throws InterruptedException {
        return R.success(u);
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        log.info("dddddddddddddd");
        return "index.html";
    }


    @GetMapping("/beanList")
    public String beanList() {
        return JSON.toJSONString(applicationContext.getBeanDefinitionNames());
    }

    private List<SysUser> getUsersFromService() {
        try {
            Thread.sleep(2000000); // 模拟耗时操作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        return Collections.singletonList(sysUser);
    }
}
