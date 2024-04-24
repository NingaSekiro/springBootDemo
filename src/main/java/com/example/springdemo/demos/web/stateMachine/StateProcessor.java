//package com.example.springdemo.demos.web.stateMachine;
//
//import com.example.springdemo.demos.web.stateMachine.enums.Events;
//import com.example.springdemo.demos.web.stateMachine.enums.States;
//import com.example.springdemo.demos.web.stateMachine.model.Order;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.persist.StateMachinePersister;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//@Slf4j
//public class StateProcessor {
//    @Resource
//    private StateMachine<States, Events> stateMachine;
//    @Resource
//    private StateMachinePersister<States, Events, States> persister;
//
//    public Boolean process(Order order, Events events){
//        Message<Events> message = MessageBuilder.withPayload(events).setHeader("order", order).build();
//        return sendEvent(message);
//    }
//
//    private Boolean sendEvent(Message<Events> message){
//        try {
//            Order order = message.getHeaders().get("order", Order.class);
//            persister.restore(stateMachine, order.getState());
//            boolean b = stateMachine.sendEvent(message);
//            if (stateMachine.hasStateMachineError()){
//                log.error("stateMachine has error" );
//            }
//            persister.persist(stateMachine, order.getState());
//            return b;
//        } catch (Throwable e) {
//            log.error("sendEvent error", e);
//        }
//        return false;
//    }
//
//}
