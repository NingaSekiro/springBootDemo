package com.example.springdemo.demos.web.runnable;

import com.example.springdemo.demos.web.config.CustomRequestScopeAttr;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class  SchedulerRunnable implements Runnable {
    @Setter
    private List<String> list;

//    private final DynamicTaskService dynamicTaskService;
    public void run() {
//        log list
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            RequestContextHolder.setRequestAttributes(requestAttributes, true);
            RequestContextHolder.setRequestAttributes(new CustomRequestScopeAttr());
            log.info("runnable start");
            log.info("list: {}", list);
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
//        dynamicTaskService.stopTask("1");
//        log.info("stop task 1");
    }
}
