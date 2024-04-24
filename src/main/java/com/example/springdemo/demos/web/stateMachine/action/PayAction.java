package com.example.springdemo.demos.web.stateMachine.action;

import com.example.springdemo.demos.web.db.mapper.SysUserMapper;
import com.example.springdemo.demos.web.db.mapper.TaskMapper;
import com.example.springdemo.demos.web.model.SysUser;
import com.example.springdemo.demos.web.model.Task;
import com.example.springdemo.demos.web.stateMachine.enums.Events;
import com.example.springdemo.demos.web.stateMachine.enums.States;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PayAction implements Action<States, Events> {
    private final SysUserMapper sysUserMapper;
    private final TaskMapper taskMapper;

    @Override
    public void execute(StateContext<States, Events> context) {
        // 获取当前状态和事件
        States currentState = context.getStateMachine().getState().getId();
        Events currentEvent = context.getEvent();
        List<SysUser> sysUsers = sysUserMapper.selectByProgress(50);
        sysUsers.forEach(sysUser -> {
            sysUser.setProgress(50);
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        });
        Task task = taskMapper.selectByProgress(100);
        task.setProgress(50);
        taskMapper.updateByPrimaryKeySelective(task);
        // 可以访问和修改上下文中的变量
//        context.getExtendedState().getVariables().put("myVariable", "Some value");
    }
}
