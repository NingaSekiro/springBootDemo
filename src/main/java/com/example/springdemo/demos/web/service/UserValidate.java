package com.example.springdemo.demos.web.service;

import com.example.springdemo.demos.web.exception.myException;
import com.example.springdemo.demos.web.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserValidate {
    public void validate(User user) throws myException {
        if (user.getAge() != 0) {
            throw new myException("必须为0");
        }
    }

    public void validateLength(User user) throws myException {
        if (user.getName().length() < 3) {
            throw new myException("长度小于3");
        }
    }
}
