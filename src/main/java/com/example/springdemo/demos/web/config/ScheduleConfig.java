package com.example.springdemo.demos.web.config;

import com.example.springdemo.demos.web.stateMachine.StateProcessor;
import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import com.example.springdemo.demos.web.stateMachine.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ScheduleConfig {

    @Resource
    private StateProcessor stateProcessor;
    @Resource
    private StateMachine<States, Events> stateMachine;

    @Scheduled(fixedRate = 1000000)
//    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void myTasks3(){


        Order order = null;
        try {
            order = new Order();
            order.setState(States.UNPAID);
            stateProcessor.process(order, Events.PAY);
            if (stateMachine.hasStateMachineError()){
                log.error("ddd");
            }
            stateProcessor.process(order, Events.RECEIVE);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        log.info("order state is：{}",order.getState());
    }
}
