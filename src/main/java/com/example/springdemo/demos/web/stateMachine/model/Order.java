package com.example.springdemo.demos.web.stateMachine.model;

import com.example.springdemo.demos.web.stateMachine.enums.States;
import lombok.Data;

@Data
public class Order {
    private States state;
}
