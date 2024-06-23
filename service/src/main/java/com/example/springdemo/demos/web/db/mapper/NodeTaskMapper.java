package com.example.springdemo.demos.web.db.mapper;

import com.example.springdemo.demos.web.model.NodeTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NodeTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NodeTask record);

    int insertSelective(NodeTask record);

    NodeTask selectByPrimaryKey(Long id);
    List<NodeTask> selectAll();

    int updateByPrimaryKeySelective(NodeTask record);

    int updateByPrimaryKey(NodeTask record);

    int batchInsert(@Param("list") List<NodeTask> list);
}