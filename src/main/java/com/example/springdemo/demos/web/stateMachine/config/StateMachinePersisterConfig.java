package com.example.springdemo.demos.web.stateMachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;

import java.util.HashMap;
import java.util.Map;

// 状态机持久化的配置类
@Configuration
public class StateMachinePersisterConfig {
  /**
   * 内存持久化配置
   */
  @Bean
  public DefaultStateMachinePersister persister() {
    return new DefaultStateMachinePersister(new StateMachinePersist() {
      //用户保存所有状态机上下文
      private Map map = new HashMap();
 
      // 持久化状态机
      @Override
      public void write(StateMachineContext context, Object contextObj) throws Exception {
        System.out.println("持久化状态机：contextObj：" + contextObj.toString());
        map.put(contextObj, context);
      }
 
      // 获取状态机
      @Override
      public StateMachineContext read(Object contextObj) throws Exception {
        System.out.println("获取状态机，contextObj：" + contextObj.toString());
        StateMachineContext stateMachineContext = (StateMachineContext) map.get(contextObj);
        return stateMachineContext;
      }
    });
  }
}