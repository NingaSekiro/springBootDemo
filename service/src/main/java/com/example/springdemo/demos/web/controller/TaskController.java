package com.example.springdemo.demos.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {
    @GetMapping("/addTask")
    public String addTask(String type) throws Exception {
        return "dd";
    }

    @GetMapping("/editTask")
    public String editTask(String type) throws Exception {
        return "dd";
    }
}
