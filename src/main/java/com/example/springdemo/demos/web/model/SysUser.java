package com.example.springdemo.demos.web.model;

import lombok.Data;

@Data
public class SysUser {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * 姓名
    */
    private String name;

    /**
    * 年龄
    */
    private Integer age;

    /**
    * 邮箱
    */
    private String email;
}