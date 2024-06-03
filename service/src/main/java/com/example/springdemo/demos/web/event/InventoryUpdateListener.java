package com.example.springdemo.demos.web.event;

import com.example.springdemo.demos.web.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// 更新库存
@Component
@Slf4j
public class InventoryUpdateListener implements ApplicationListener<OrderCreatedEvent> {

    @Override
    @Async
    public void onApplicationEvent(OrderCreatedEvent event) {
        Order order = event.getOrder();
        log.info("更新库存，订单号：{}", order.getId());
    }
}