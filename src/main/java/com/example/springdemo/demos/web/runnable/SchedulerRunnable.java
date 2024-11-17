package com.example.springdemo.demos.web.runnable;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SchedulerRunnable implements Runnable {
    @Setter
    private List<String> list;

//        private final DynamicTaskService dynamicTaskService;
    public void run() {
//        log list
        log.info("runnable start");
        log.info("list: {}", list);
//        dynamicTaskService.stopTask("1");
//        log.info("stop task 1");
    }
}
