package com.example.springdemo.demos.web.runnable;

import com.example.springdemo.demos.web.service.DynamicTaskService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SchedulerRunnable implements Runnable {
    @Setter
    private List<String> list;

    private final DynamicTaskService dynamicTaskService;
    public void run() {
//        log list
        log.info("list: {}", list);
//        dynamicTaskService.stopTask("1");
//        log.info("stop task 1");
    }
}
