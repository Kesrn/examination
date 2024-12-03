package com.zcx.exam.dao;

import com.zcx.exam.entity.Question;
import com.zcx.exam.entityResult.QuestionBackResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    QuestionBackResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> selectAll(Map<String, Object> map);

    void updateStatus(int id);
}