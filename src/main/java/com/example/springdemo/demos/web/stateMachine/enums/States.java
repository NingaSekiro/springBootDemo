package com.example.springdemo.demos.web.stateMachine.enums;

public enum States {
    UNPAID(0, "待支付"), // 待支付
    WAITING_FOR_RECEIVE(50, "待收货"), // 待收货
    DONE(100, "结束"); // 结束

    private final int value;
    private final String description;

    States(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static States fromValue(int value) {
        for (States state : States.values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid States value: " + value);
    }
}
