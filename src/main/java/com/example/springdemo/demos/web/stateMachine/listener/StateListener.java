package com.example.springdemo.demos.web.stateMachine.listener;

import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import com.example.springdemo.demos.web.stateMachine.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnStateMachineError;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import javax.annotation.Resource;

import static com.example.springdemo.demos.web.stateMachine.enums.States.DONE;

@WithStateMachine(name = "doSomethingMachine")
@Slf4j
public class StateListener {

    @Resource
    private StateMachine<States, Events> stateMachine;

//    @OnTransition(target = "UNPAID")
////    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
//    public void create(Message<Events> message) throws Exception {
//        log.info("订单创建，待支付");
//    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay(Message<Events> message) throws Exception {
        throw new Exception("模拟支付失败");
//        log.info("用户完成支付，待收货");
//        Order order =(Order) message.getHeaders().get("order");
//        order.setState(WAITING_FOR_RECEIVE);
    }

    @OnStateMachineError
    public void handleError(StateContext<States, Events> stateContext, Exception exception) {
        log.error("订单状态机执行异常", exception);
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive(Message<Events> message) {
        log.info("用户已收货，订单完成");
        Order order =(Order) message.getHeaders().get("order");
        order.setState(DONE);
    }
}
