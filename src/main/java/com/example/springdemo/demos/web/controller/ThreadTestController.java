package com.example.springdemo.demos.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

@RestController
@Slf4j
public class ThreadTestController {
    @GetMapping("/thread")
    public String hello() {
        // 获取Java线程管理Bean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
//        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            // System.out.println(threadInfo.toString());
            log.info("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }
        return threadInfos.toString();
    }
}
