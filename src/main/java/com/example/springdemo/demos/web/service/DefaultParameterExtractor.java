package com.example.springdemo.demos.web.service;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultParameterExtractor {

    public List<String> extractParameters(Parameter[] parameters, Object[] args, String[] paramNames) {
        if (paramNames == null || paramNames.length == 0) {
            return new ArrayList<>();
        }
        List<String> extractedParams = new ArrayList<>();
        for (String paramName : paramNames) {
            for (int i = 0; i < args.length; i++) {
                if (paramName.equals(parameters[i].getName())) {
                    extractedParams.add(JSON.toJSONString("dddddd"));
                    break;
                }
            }
        }
        return extractedParams;
    }
}