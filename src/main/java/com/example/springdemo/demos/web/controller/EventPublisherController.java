package com.example.springdemo.demos.web.controller;

import com.example.springdemo.demos.web.event.OrderService;
import com.example.springdemo.demos.web.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EventPublisherController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/event")
    public void hello() {
        Order order=new Order();
        order.setId("ddd");
        order.setName("dddd");
        order.setPrice(123);
        orderService.createOrder(order);
    }
}
