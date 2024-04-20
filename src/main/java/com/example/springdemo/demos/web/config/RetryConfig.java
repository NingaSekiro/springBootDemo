package com.example.springdemo.demos.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {

    @Value("${retry.initial-interval:1000}")
    private long initialInterval;

    @Value("${retry.max-interval:60000}")
    private long maxInterval;

    @Bean
    public RetryTemplate retryTemplate() {
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(initialInterval);
        backOffPolicy.setMaxInterval(maxInterval);

        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(backOffPolicy);
        return retryTemplate;
    }
}
