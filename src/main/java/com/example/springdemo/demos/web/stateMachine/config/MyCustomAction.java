package com.example.springdemo.demos.web.stateMachine.config;

import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class MyCustomAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        // 获取当前状态和事件
        States currentState = context.getStateMachine().getState().getId();
        Events currentEvent = context.getEvent();
        // 执行业务逻辑，例如更新数据库、发送通知等
//        System.out.println("Executing action on transition from " + currentState + " to another state due to event " + currentEvent);

        // 可以访问和修改上下文中的变量
//        context.getExtendedState().getVariables().put("myVariable", "Some value");

        // 或者使用其他Spring beans来完成复杂的操作
//        SomeService someService = context.getApplicationContext().getBean(SomeService.class);
//        someService.doSomething(context);

        // ...
    }
}
