package com.example.springdemo.demos.web.db.mapper;

import com.example.springdemo.demos.web.model.Person;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonMapper {
    int insert(Person record);

    int insertSelective(Person record);

    int batchInsert(@Param("list") List<Person> list);
}