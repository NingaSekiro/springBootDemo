package com.example.springdemo.demos.web.config;

//@Configuration
//public class RetryConfig {
//
//    @Value("${retry.initial-interval:1000}")
//    private long initialInterval;
//
//    @Value("${retry.max-interval:60000}")
//    private long maxInterval;
//
//    @Bean
//    public RetryTemplate retryTemplate() {
//        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
//        backOffPolicy.setInitialInterval(initialInterval);
//        backOffPolicy.setMaxInterval(maxInterval);
//
//        RetryTemplate retryTemplate = new RetryTemplate();
//        retryTemplate.setBackOffPolicy(backOffPolicy);
//        return retryTemplate;
//    }
//}
