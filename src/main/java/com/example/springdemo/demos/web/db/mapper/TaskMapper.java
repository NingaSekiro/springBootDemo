package com.example.springdemo.demos.web.db.mapper;

import com.example.springdemo.demos.web.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long id);

    Task selectByProgress(Integer progress);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    int batchInsert(@Param("list") List<Task> list);
}