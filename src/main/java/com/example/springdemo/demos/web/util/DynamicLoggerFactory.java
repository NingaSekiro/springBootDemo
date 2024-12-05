package com.example.springdemo.demos.web.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class DynamicLoggerFactory {

    private static final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
    private static final Configuration config = ctx.getConfiguration();

    public static Logger getLogger(String name, String filePath) {
        Logger logger = LogManager.getLogger(name);
        org.apache.logging.log4j.core.Logger coreLogger = (org.apache.logging.log4j.core.Logger) logger;

        // 检查是否已经存在同名的 Appender
        if (!coreLogger.getAppenders().containsKey(name + "-FileAppender")) {
            Layout<?> layout = PatternLayout.newBuilder()
                    .withPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n")
                    .build();

            Appender appender = FileAppender.newBuilder()
                    .setName(name + "-FileAppender")
                    .withFileName(filePath)
                    .setLayout(layout)
                    .build();

            appender.start();  // 启动 Appender
            config.addAppender(appender);
            coreLogger.addAppender(appender);
        }
        return logger;
    }

    public static void removeLogger(String name) {
        Logger logger = LogManager.getLogger(name);
        org.apache.logging.log4j.core.Logger coreLogger = (org.apache.logging.log4j.core.Logger) logger;

        for (Appender appender : coreLogger.getAppenders().values()) {
            coreLogger.removeAppender(appender);
            appender.stop();  // 停止并释放 Appender 的资源
        }
    }

    public static void main(String[] args) {
        // 创建一个日志记录器，输出到 application.log
        Logger appLogger = getLogger("ApplicationLogger", "logs/application.log");
        appLogger.info("This is an info message from the application logger.");
        appLogger.info("This is another info message from the application logger.");

        // 创建另一个日志记录器，输出到 error.log
        Logger errorLogger = getLogger("ErrorLogger", "logs/error.log");
        errorLogger.error("This is an error message from the error logger.");
        errorLogger.error("This is another error message from the error logger.");

        // 移除日志记录器
        removeLogger("ApplicationLogger");
        removeLogger("ErrorLogger");
        appLogger.info("This is another info message from the application logger.");

    }
}
