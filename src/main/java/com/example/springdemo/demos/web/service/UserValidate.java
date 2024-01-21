package com.example.springdemo.demos.web.service;

import com.example.springdemo.demos.web.exception.BusinessException;
import com.example.springdemo.demos.web.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserValidate {
    public void validate(User user) {
        if (user.getAge() != 0) {
            throw new BusinessException("不能为空");
        }
    }

    public void validateLength(User user) {
        if (user.getName().length() < 3) {
            throw new BusinessException("长度小于3");
        }
    }
}
