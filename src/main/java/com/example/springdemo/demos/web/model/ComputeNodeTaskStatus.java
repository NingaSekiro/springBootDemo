package com.example.springdemo.demos.web.model;

public enum ComputeNodeTaskStatus {
    CREATED, PENDING, IN_PROGRESS, SUCCESS, FAILURE;
    public boolean isTerminal() {
        return this == SUCCESS || this == FAILURE;
    }
}
