package com.example.springdemo.demos.web.model;

import org.springframework.context.ApplicationEvent;

public class ComputeNodeTaskStatusChangeEvent extends ApplicationEvent {
    private final ComputeNodeTask task;
    private final ComputeNodeTaskStatus nextStatus;

    public ComputeNodeTaskStatusChangeEvent(ComputeNodeTask task, ComputeNodeTaskStatus nextStatus) {
        super(task);
        this.task = task;
        this.nextStatus = nextStatus;
    }

    public ComputeNodeTask getTask() {
        return task;
    }

    public ComputeNodeTaskStatus getNextStatus() {
        return nextStatus;
    }
}
