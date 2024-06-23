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
import com.example.springdemo.demos.web.model.R;
import com.example.springdemo.demos.web.model.SysUser;
import com.example.springdemo.demos.web.service.ComputeNodeTaskProcessor;
import com.example.springdemo.demos.web.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//     http://127.0.0.1:8080/hello?name=lisi
//    @RequestMapping("/hello")
//    public String hello() throws InterruptedException {
//        Thread.sleep(3000);
//        return Thread.currentThread().getName();
//    }

//    @OperationLog("T(com.example.springdemo.demos.web.util.ValidateUtil).test(#s)")
    @GetMapping("/hello")
    public String hello(String s) throws Exception {
//        computeNodeTaskProcessor.processTask("ddd");
        return "dd";
    }

    //     http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    public  R saveUser(SysUser u) throws InterruptedException {
        sysUserService.saveUser(u);
        return R.success(u);
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
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
