package com.example.springdemo.demos.web.stateMachine.config;

import com.example.springdemo.demos.web.model.Task;
import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserStateMachinePersisterConfig {
    private final SysUserStateMachinePersist sysUserStateMachinePersist;

    @Bean(name = "sysUserStateMachinePersister")
    public StateMachinePersister<States, Events, Task> orderPersister() {
        return new DefaultStateMachinePersister<States, Events, Task>(sysUserStateMachinePersist);
    }
}