package com.example.springdemo.demos.web.exception;


/**
 * 自定义异常类
 *
 * @author yupi
 */
public class BusinessException extends RuntimeException {

    private final int code;

    private final String description;

    public BusinessException(String message) {
        super(message);
        this.code = 500;
        this.description = "未知错误";
    }

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
