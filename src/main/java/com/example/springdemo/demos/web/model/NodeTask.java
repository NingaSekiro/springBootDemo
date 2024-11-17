package com.example.springdemo.demos.web.model;

import lombok.Data;

@Data
public class NodeTask {
    private Long id;

    private Integer progress;

    private String step;
}