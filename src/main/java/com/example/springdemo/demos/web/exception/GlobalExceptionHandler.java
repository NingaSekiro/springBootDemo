package com.example.springdemo.demos.web.exception;

import com.example.springdemo.demos.web.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author yupi
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public R businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return R.error(e.getDescription());
    }

//    @ExceptionHandler(RuntimeException.class)
//    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
//        log.error("runtimeException", e);
//        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
//    }
}
