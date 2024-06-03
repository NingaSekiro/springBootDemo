package com.example.springdemo.demos.web.event;

import com.example.springdemo.demos.web.model.Order;
import org.springframework.context.ApplicationEvent;

public class OrderCreatedEvent extends ApplicationEvent {

    private final Order order;

    public OrderCreatedEvent(Order order) {
        super(order);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
