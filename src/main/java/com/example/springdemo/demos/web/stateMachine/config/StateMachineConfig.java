package com.example.springdemo.demos.web.stateMachine.config;

import com.example.springdemo.demos.web.stateMachine.action.ErrorHandlerAction;
import com.example.springdemo.demos.web.stateMachine.action.PayAction;
import com.example.springdemo.demos.web.stateMachine.action.ReceiveAction;
import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import com.example.springdemo.demos.web.stateMachine.guard.MyCustomGuard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine(name = "doSomethingMachine")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    private final PayAction payAction;
    private final ErrorHandlerAction errorHandlerAction;
    private final MyCustomGuard myCustomGuard;
    private final ReceiveAction receiveAction;

//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
//        config
//                .withConfiguration();
//                .listener(new StateMachineListenerAdapter<States, Events>() {
//                    @Override
//                    public void stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {
//                        log.error("dddddd");
//                    }
//                });
//    }
    // 状态机状态配置
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        // 定义状态机中的状态
        states.withStates().initial(States.UNPAID) // 初始状态
                .end(States.DONE)
                .states(EnumSet.allOf(States.class));
    }

    // 状态机转换配置
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)
                .event(Events.PAY) // 指定状态来源和目标
                .action(payAction)
//                .guard(myCustomGuard)
                .and() // 指定触发事件
//                .withChoice()
//                .source(States.STATE_FIRST)
//                .first(States.WAITING_FOR_RECEIVE, new OrderCheckGuard(),
//                  new OrderCheckPassedAction(), new ErrorHandlerAction())
//                .then(States.STATE_THIRD, new MtGuard2())
//                .then(States.STATE_FOURTH, new MtGuard3())
//                .last(States.STATE_FIFTH)
//                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE)
                .action(receiveAction);
    }

//    @Bean
//    public DefaultStateMachinePersister stateMachinePersister() {
//        return new DefaultStateMachinePersister<>(new StateMachinePersist<Object, Object, Order>() {
//            @Override
//            public void write(StateMachineContext<Object, Object> stateMachineContext, Order order) {
//
//            }
//
//            @Override
//            public StateMachineContext<Object, Object> read(Order order) {
//                return new DefaultStateMachineContext(order.getState(), null, null, null);
//            }
//        });
//
//    }

    //@Override
    //public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
    //    config
    //            .withConfiguration()
    //                .listener(listener());  // 指定状态机的处理监听器
    //}

    //@Bean
    //public StateMachineListener<States, Events> listener() {
    //    return new StateMachineListenerAdapter<States, Events>() {
    //        @Override
    //        public void transition(Transition<States, Events> transition) {
    //            if (transition.getTarget().getId() == States.UNPAID) {
    //                logger.info("订单创建，待支付");
    //                return;
    //            }
    //
    //            if (transition.getSource().getId() == States.UNPAID && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
    //                logger.info("用户完成支付，待收货");
    //                return;
    //            }
    //
    //            if (transition.getSource().getId() == States.WAITING_FOR_RECEIVE && transition.getTarget().getId() == States.DONE) {
    //                logger.info("用户已收货，订单完成");
    //                return;
    //            }
    //        }

            // 在状态机开始进行状态转换时调用
//            @Override
//            public void transitionStarted(Transition<States, Events> transition) {
//                // 从未支付->待收货状态
//                if(transition.getSource().getId() == States.UNPAID
//                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
//                    System.out.println("用户支付（状态转换开始）");
//                    return;
//                }
//            }
//
//            // 在状态机进行状态转换结束时调用
//            @Override
//            public void transitionEnded(Transition<States, Events> transition) {
//                // 从未支付->待收货状态
//                if(transition.getSource().getId() == States.UNPAID
//                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
//                    System.out.println("用户支付（状态转换结束）");
//                    return;
//                }
//            }
    //    };
    //}

}
