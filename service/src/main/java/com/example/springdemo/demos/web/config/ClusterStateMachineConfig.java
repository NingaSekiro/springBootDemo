package com.example.springdemo.demos.web.config;

import com.example.springdemo.StateMachine;
import com.example.springdemo.builder.StateMachineBuilder;
import com.example.springdemo.builder.StateMachineBuilderFactory;
import com.example.springdemo.demos.web.colaStateMachine.context.ClusterContext;
import com.example.springdemo.demos.web.colaStateMachine.events.ClusterEvents;
import com.example.springdemo.demos.web.colaStateMachine.states.ClusterStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.springdemo.demos.web.colaStateMachine.events.ClusterEvents.*;


@Configuration

@Slf4j
public class ClusterStateMachineConfig {
    @Bean
    public StateMachine<ClusterStates, ClusterEvents, ClusterContext> clusterStateMachine() {
        StateMachineBuilder<ClusterStates, ClusterEvents, ClusterContext> builder = StateMachineBuilderFactory.create();
        // Deploy
        builder.externalTransition()
                .from(ClusterStates.INIT)
                .to(ClusterStates.DEPLOY_INSPECT)
                .on(DEPLOY);
        builder.externalTransition()
                .from(ClusterStates.DEPLOY_INSPECT)
                .to(ClusterStates.RESOURCE)
                .on(DEPLOY);
        builder.externalTransition()
                .from(ClusterStates.RESOURCE)
                .to(ClusterStates.PXE)
                .on(DEPLOY);
        builder.externalTransition()
                .from(ClusterStates.PXE)
                .to(ClusterStates.CONFIG)
                .on(DEPLOY);

        // Edit
        builder.externalTransition()
                .from(ClusterStates.INIT)
                .to(ClusterStates.RESOURCE)
                .on(EDIT_NOT_REBOOT);

        // Edit_REBOOT
        builder.externalTransition()
                .from(ClusterStates.INIT)
                .to(ClusterStates.EDIT_INSPECT)
                .on(EDIT_REBOOT);
        builder.externalTransition()
                .from(ClusterStates.EDIT_INSPECT)
                .to(ClusterStates.RESOURCE)
                .on(EDIT_REBOOT);
        builder.externalTransition()
                .from(ClusterStates.RESOURCE)
                .to(ClusterStates.PXE)
                .on(EDIT_REBOOT);
        builder.externalTransition()
                .from(ClusterStates.PXE)
                .to(ClusterStates.CONFIG)
                .on(EDIT_REBOOT);


        StateMachine<ClusterStates, ClusterEvents, ClusterContext> clusterMachine = builder.build("clusterMachine");
        String plantUML = clusterMachine.generatePlantUML();
        log.info(plantUML);
        return clusterMachine;
    }
}
