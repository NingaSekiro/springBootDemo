package com.example.springdemo.demos.web.stateMachine.listener;

import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultStateMachineListener extends StateMachineListenerAdapter<States, Events> {

    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        log.info("stateChanged from:{}, to:{}", from, to);
    }

    @Override
    public void eventNotAccepted(Message<Events> event) {
    }

    @Override
    public void stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {
        log.error("stateMachineError", exception);
    }
    @Override
    public void transitionEnded(Transition<States, Events> transition) {
    }
}