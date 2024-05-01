package com.example.springdemo.demos.web.config;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.ScheduledFuture;

public class CustomThreadPoolTaskScheduler extends ThreadPoolTaskScheduler {

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        RequestAttributes context = RequestContextHolder.currentRequestAttributes();
        Runnable taskWithRequestContext = () -> {
            try {
                RequestContextHolder.setRequestAttributes(context);
                task.run();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        };
        return super.scheduleWithFixedDelay(taskWithRequestContext, delay);
    }
}
