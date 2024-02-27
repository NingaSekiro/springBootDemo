package com.example.springdemo.demos.web.service;

import com.example.springdemo.demos.web.db.mapper.SysUserMapper;
import com.example.springdemo.demos.web.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;


    @Transactional
    public void insert(SysUser sysUser) {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("main operation executing in thread: " + currentThreadName);
        sysUserMapper.insert(sysUser);
    }
}
