package com.example.springdemo.demos.web.controller;

import com.example.springdemo.demos.web.db.mapper.PersonMapper;
import com.example.springdemo.demos.web.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MybatisPerformController {
    private final PersonMapper personMapper;

    @GetMapping("/batchInsert")
    public void batchInsert(@RequestParam("count") Integer count) {
        List<Person> personList = new ArrayList<>();
        extracted(personList, count);
        long start = System.currentTimeMillis();
        personMapper.batchInsert(personList);
        long end = System.currentTimeMillis();
        long costInMilliseconds = end - start;
        double costInSeconds = (double) costInMilliseconds / 1000;
        log.info("batchInsert cost:{}", costInSeconds);
    }

    private void extracted(List<Person> personList, Integer count) {
        Person person = new Person();
        getRandomString();
        person.setName(getRandomString());
        person.setEmail(getRandomString());
        person.setPro1(getRandomString());
        person.setPro2(getRandomString());
        person.setPro3(getRandomString());
        person.setPro4(getRandomString());
        person.setPro5(getRandomString());
        person.setPro6(getRandomString());
        person.setPro7(getRandomString());
        person.setPro8(getRandomString());
        person.setPro9(getRandomString());
        person.setPro10(getRandomString());
        person.setPro11(getRandomString());
        person.setPro12(getRandomString());
        person.setPro13(getRandomString());
        person.setPro14(getRandomString());
        for (int i = 0; i < count; i++) {
            personList.add(person);
        }
    }

    private String getRandomString() {
        Random random = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            randomStringBuilder.append((char) ('a' + random.nextInt(26))); // 生成小写字母的随机字符
        }
        return randomStringBuilder.toString();
    }
}
