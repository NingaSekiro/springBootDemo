package com.example.springdemo.demos.web.config;

import com.example.springdemo.demos.web.db.mapper.SysUserMapper;
import com.example.springdemo.demos.web.db.mapper.TaskMapper;
import com.example.springdemo.demos.web.model.SysUser;
import com.example.springdemo.demos.web.model.Task;
import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleConfig {
    private final StateMachine<States, Events> stateMachine;
    private final SysUserMapper sysUserMapper;
    private final TaskMapper taskMapper;
    private final StateMachinePersister<States, Events, Task> persister;

    @Scheduled(fixedDelay = 1000000)
//    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void myTasks3() throws Exception {
        List<SysUser> sysUsers = sysUserMapper.selectByProgress(100);
        Integer minProgress = sysUsers.stream()
                .map(SysUser::getProgress)
                .min(Integer::compareTo)
                .orElse(null);
        Task nowTask = new Task();
        nowTask.setId(1L);
        nowTask.setProgress(minProgress);
        persister.restore(stateMachine, nowTask);
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }
}
