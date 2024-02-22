package com.example.springdemo.demos.web.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SysUser {
    /**
    * 主键ID
    */
    @NotNull
    private Long id;

    /**
    * 姓名
    */
    @NotNull
    private String name;

    /**
    * 年龄
    */
    @NotNull
    private Integer age;

    /**
    * 邮箱
    */
    @NotNull
    private String email;
}