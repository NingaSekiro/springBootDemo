package com.example.springdemo.demos.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class DynamicTaskService {

    private final TaskScheduler taskScheduler;
    private final Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();

    public void startTask(String taskId, String cronExpression, Runnable task) {
        System.out.println("尝试为任务 " + taskId + " 设置Cron表达式: " + cronExpression);
        CronTrigger trigger = new CronTrigger(cronExpression);
        if (taskFutures.containsKey(taskId)) {
            log.info("任务 " + taskId + " 已存在，无法再次启动。");
            return;
        }
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(task, trigger);
        taskFutures.put(taskId, scheduledFuture);
        System.out.println("任务 " + taskId + " 已启动。");
    }

    public void stopTask(String taskId) {
        ScheduledFuture<?> future = taskFutures.get(taskId);
        if (future != null) {
            boolean cancelled = future.cancel(false);
            if (cancelled) {
                System.out.println("任务 " + taskId + " 已停止。");
            } else {
                System.out.println("任务 " + taskId + " 停止失败，可能已经在执行或已停止。");
            }
            taskFutures.remove(taskId);
        } else {
            System.out.println("找不到任务ID为 " + taskId + " 的任务。");
        }
    }
}