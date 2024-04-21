package com.example.springdemo.demos.web.stateMachine.config;

import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
public class MyCustomGuard implements Guard<States, Events> {

    @Override
    public boolean evaluate(StateContext<States, Events> context) {
        // 获取当前状态和事件
        States currentState = context.getStateMachine().getState().getId();
        Events currentEvent = context.getEvent();

        // 评估是否允许状态迁移
        boolean canTransition = checkSomeBusinessRule(currentState, currentEvent);

        // 可能需要访问或修改上下文中的变量来辅助判断
        String someValue = (String) context.getExtendedState().getVariables().get("someKey");

        return canTransition;
    }

    private boolean checkSomeBusinessRule(States state, Events event) {
        // 实现具体的业务规则检查，返回布尔值表示是否允许状态迁移
        // ...
        return true;
    }
}
