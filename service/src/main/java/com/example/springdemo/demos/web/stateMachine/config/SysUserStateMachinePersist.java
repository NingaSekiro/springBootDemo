package com.example.springdemo.demos.web.stateMachine.config;

import com.example.springdemo.demos.web.model.Task;
import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

@Component
public class SysUserStateMachinePersist implements StateMachinePersist<States, Events, Task> {

    @Override
    public void write(StateMachineContext<States, Events> context, Task contextObj) throws Exception {
        //这里不做任何持久化工作
    }

    @Override
    public StateMachineContext<States, Events> read(Task contextObj) throws Exception {
        StateMachineContext<States, Events> result = new DefaultStateMachineContext<States, Events>(States.fromValue(contextObj.getProgress()),
                null, null, null, null, "orderMachine");
        return result;
    }
}
