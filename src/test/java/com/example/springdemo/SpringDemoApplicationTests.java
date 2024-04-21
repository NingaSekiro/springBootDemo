package com.example.springdemo;

import com.example.springdemo.demos.web.stateMachine.StateProcessor;
import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import com.example.springdemo.demos.web.stateMachine.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class SpringDemoApplicationTests {

    @Resource
    private StateProcessor stateProcessor;
    @Test
    void pay(){
        Order order=new Order();
        order.setState(States.UNPAID);
        try {
            stateProcessor.process(order,Events.PAY);
        } catch (Exception e) {
            log.error("error",e);
        }
        log.info("order state is {}",order.getState());
        try {
            stateProcessor.process(order,Events.RECEIVE);
        } catch (Exception e) {
            log.error("error",e);
        }
    }


}
