package com.example.springdemo.demos.web.service;

import com.example.springdemo.demos.web.model.ComputeNodeTask;
import com.example.springdemo.demos.web.model.ComputeNodeTaskStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ComputeNodeTaskCenter {
    private final Map<String, ComputeNodeTask> taskMap = new ConcurrentHashMap<>();

    public void addTask(ComputeNodeTask task) {
        taskMap.put(task.getTaskId(), task);
    }

    public ComputeNodeTask getTask(String taskId) {
        return taskMap.get(taskId);
    }

    public void updateTaskStatus(String taskId, ComputeNodeTaskStatus newStatus) {
        ComputeNodeTask task = taskMap.get(taskId);
        if (task != null) {
            task = new ComputeNodeTask(task.getTaskId(), newStatus);
            taskMap.put(taskId, task);
        }
    }

    // 其他相关方法...
}
