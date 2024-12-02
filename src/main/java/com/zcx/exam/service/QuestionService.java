package com.zcx.exam.service;

import com.zcx.exam.entity.Question;
import com.zcx.exam.entityResult.QuestionBackResult;

import java.util.List;
import java.util.Map;

public interface QuestionService {

     List<Question> selectAll(Map<String, Object> map);

     void updateStatus(int id);

     QuestionBackResult selectByOne(Integer id);

     void addQuestionPaper(Question question);
}
