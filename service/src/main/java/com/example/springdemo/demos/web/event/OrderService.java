package com.example.springdemo.demos.web.event;

import com.example.springdemo.demos.web.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private  ApplicationEventPublisher eventPublisher;

    public void createOrder(Order order) {
        // 实现订单创建逻辑...

        // 订单创建成功后，发布事件
        eventPublisher.publishEvent(new OrderCreatedEvent(order));
    }
}
