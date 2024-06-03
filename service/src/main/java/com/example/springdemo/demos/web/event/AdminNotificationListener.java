package com.example.springdemo.demos.web.event;

import com.example.springdemo.demos.web.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// 向管理员发送短信提醒
@Component
@Slf4j
public class AdminNotificationListener implements ApplicationListener<OrderCreatedEvent> {

    private static final double THRESHOLD_AMOUNT = 1000.0; // 假设阈值为1000元

    @Override
    @Async
    public void onApplicationEvent(OrderCreatedEvent event) {
        Order order = event.getOrder();
        log.info("AdminNotificationListener: 收到订单创建事件，订单金额：{}", order.getPrice());
    }
}