package com.example.springdemo.demos.web.event;

import com.example.springdemo.demos.web.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailNotificationListener implements ApplicationListener<OrderCreatedEvent> {

    @Override
    @Async

    public void onApplicationEvent(OrderCreatedEvent event) {
        Order order = event.getOrder();
        log.info("Sending email to customer for order {}",  order.getId());
    }
}