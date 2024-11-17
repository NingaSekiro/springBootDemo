package com.example.springdemo.demos.web.anno;

import com.example.springdemo.demos.web.service.DefaultParameterExtractor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    @Autowired
    private DefaultParameterExtractor defaultParameterExtractor;

    //    @Around("@annotation(operationLog)")
//    public Object logOperation(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        Parameter[] parameters = method.getParameters();
//        Object[] args = joinPoint.getArgs();
//        List<String> strings = defaultParameterExtractor.extractParameters(parameters, args,
//                operationLog.paramNames());
//
//
//        // 执行目标方法
//        return joinPoint.proceed();
//    }
    @Around("@annotation(operationLog)")
    public Object logOperation(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = operationLog.paramNames();
        Method method = signature.getMethod();

        Object[] args = joinPoint.getArgs();
        Map<String, Object> logMap = new HashMap<>();

        for (String paramName : paramNames) {
            String[] parts = paramName.split("\\.");
            String paramKey = parts[0]; // SysUser
            String attrPath = String.join(".", Arrays.copyOfRange(parts, 1, parts.length)); // id 或 name 或 nested.property
            int paramIndex = -1;
            for (int i = 0; i < method.getParameters().length; i++) {
                if (parts[0].equals(method.getParameters()[i].getName())) {
                    paramIndex = i;
                    break;
                }
            }
            if (paramIndex == -1) {
                continue; // 参数名未找到
            }

            Object arg = args[paramIndex];
            Object value = getValueByPath(arg, attrPath);
            if (value != null) {
                logMap.put(paramName, value);
            }
        }

        System.out.println("Operation Log: " + logMap);

        return joinPoint.proceed();
    }

    private Object getValueByPath(Object obj, String path) {
        if (obj == null || path.isEmpty()) {
            return null;
        }

        String[] parts = path.split("\\.");
        Object currentObj = obj;
        for (String part : parts) {
            try {
                Field field = currentObj.getClass().getDeclaredField(part);
                field.setAccessible(true);
                currentObj = field.get(currentObj);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle exception
                e.printStackTrace();
                return null;
            }
        }
        return currentObj;
    }
}
