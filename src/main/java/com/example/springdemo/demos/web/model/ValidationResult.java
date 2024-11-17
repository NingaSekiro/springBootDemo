package com.example.springdemo.demos.web.model;
 
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Map;
 
@Data
public class ValidationResult {
    /**
     * 是否有异常
     */
    private boolean hasErrors;
 
    /**
     * 异常消息记录
     */
    private Map<String, String> errorMsg;
 
    /**
     * 获取异常消息组装
     *
     * @return
     */
    public String getMessage() throws ClassNotFoundException {
        if (errorMsg == null || errorMsg.isEmpty()) {
            return StringUtils.EMPTY;
        }
        StringBuilder message = new StringBuilder();
        errorMsg.forEach((key, value) -> {
            message.append(MessageFormat.format("{0}:{1} \r\n", key, value));
        });
        return message.toString();
    }
 
}