package com.example.springdemo.demos.web.service;

import com.example.springdemo.demos.web.db.mapper.SysUserMapper;
import com.example.springdemo.demos.web.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;


    public void insert(SysUser sysUser) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("main operation executing in thread: " + currentThreadName);
//        if (sysUserMapper.selectByPrimaryKey(sysUser.getId()) == null) {
        sysUserMapper.insert(sysUser);
//        }
    }
}
