package com.example.springdemo.demos.web.service;

import com.example.springdemo.demos.web.model.ComputeNodeTaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ComputeNodeTaskProcessor implements ApplicationEventPublisherAware {
    @Autowired
    private ComputeNodeTaskCenter taskCenter;
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void processTask(String taskId) throws Exception {
        log.info("ddddd");
        throw new Exception("ddd");
//        ComputeNodeTask task = taskCenter.getTask(taskId);
//
//        while (!task.isTerminal()) {
//            ComputeNodeTaskStatus currentStatus = task.getStatus();
//
//            switch (currentStatus) {
//                case CREATED:
//                    // 处理创建状态的任务逻辑，例如：发送纳管指令到计算节点
//                    break;
//                case PENDING:
//                    // 处理待处理状态的任务逻辑，例如：检查计算节点响应
//                    break;
//                // ... 其他状态处理逻辑
//
//                default:
//                    throw new IllegalStateException("Unknown task status: " + currentStatus);
//            }
//
//            // 根据当前状态确定下一个状态（这里仅为示例，实际应根据业务逻辑确定）
//            ComputeNodeTaskStatus nextStatus = getNextStatus(currentStatus);
//
//            // 更新任务状态并发布状态变更事件
//            taskCenter.updateTaskStatus(taskId, nextStatus);
//            eventPublisher.publishEvent(new ComputeNodeTaskStatusChangeEvent(task, nextStatus));
//        }
    }

    private ComputeNodeTaskStatus getNextStatus(ComputeNodeTaskStatus currentStatus) {
        // 根据当前状态和业务逻辑确定下一个状态
        // 这里仅为示例，实际应根据业务逻辑实现
        return ComputeNodeTaskStatus.IN_PROGRESS;
    }
}
