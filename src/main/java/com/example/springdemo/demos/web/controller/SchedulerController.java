package com.example.springdemo.demos.web.controller;

import com.example.springdemo.demos.web.runnable.SchedulerRunnable;
import com.example.springdemo.demos.web.service.DynamicTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
//@Scope(value = WebApplicationContext.SCOPE_REQUEST)
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SchedulerController {
    private final DynamicTaskService taskService;
//    private final ApplicationContext context;
    private final SchedulerRunnable schedulerRunnable;

    @PostMapping("/startTask")
    public ResponseEntity<String> startTask(@RequestParam String taskId,
                                            @RequestParam String cronExpression) throws InterruptedException {
//        Runnable dummyTask = () -> System.out.println("任务执行：" + taskId + " - 当前时间：" + new java.util.Date());
        List<String> taskIds = new ArrayList<>();
        taskIds.add(taskId);
//        for (int i = 0; i < 100; i++) {
//            taskIds.add( String.valueOf(i));
//        }
//        SchedulerRunnable schedulerRunnable = context.getBean(SchedulerRunnable.class);
        schedulerRunnable.setList(taskIds);
        if (taskId.equals("1")) {
            Thread.sleep(20000);
        }
        taskService.startTask(taskId, cronExpression, schedulerRunnable);
        return ResponseEntity.ok("任务启动指令已接受。");
    }

    @PostMapping("/stopTask")
    public ResponseEntity<String> stopTask(@RequestParam String taskId) {
        taskService.stopTask(taskId);
        return ResponseEntity.ok("任务停止指令已接受。");
    }
}
