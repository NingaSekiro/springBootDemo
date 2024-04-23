package com.example.springdemo.demos.web.db.mapper;

import com.example.springdemo.demos.web.model.Task;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    int batchInsert(@Param("list") List<Task> list);
}