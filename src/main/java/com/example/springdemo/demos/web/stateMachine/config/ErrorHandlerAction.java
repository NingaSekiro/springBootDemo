package com.example.springdemo.demos.web.stateMachine.config;

import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
// 异常处理Action
public class ErrorHandlerAction implements Action<States, Events> {
  // 执行方法
  @Override
  public void execute(StateContext<States, Events> context) {
    RuntimeException exception = (RuntimeException) context.getException();
    System.out.println("捕获到异常：" + exception);
    //将发生的异常信息记录在StateMachineContext中，在外部可以根据这个这个值是否存在来判断是否有异常发生。
    context.getStateMachine()
            .getExtendedState().getVariables()
            .put(RuntimeException.class, exception);
  }
}