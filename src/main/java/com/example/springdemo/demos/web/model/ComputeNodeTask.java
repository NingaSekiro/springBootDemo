package com.example.springdemo.demos.web.model;


import lombok.Data;

@Data
public class ComputeNodeTask {
    private final String taskId;
//    private final ComputeNodeInfo nodeInfo;
    private final ComputeNodeTaskStatus status;


    public boolean isTerminal() {
        return status.isTerminal();
    }
}

