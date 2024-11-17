package com.example.springdemo.demos.web.service;

import com.example.springdemo.demos.web.db.mapper.SysUserMapper;
import com.example.springdemo.demos.web.model.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserService {
    private final SysUserMapper sysUserMapper;

    public Integer saveUser(SysUser u) {
        return sysUserMapper.insert(u);
    }
}
