package com.zcx.exam.service.impl;

import com.zcx.exam.dao.QuestionMapper;
import com.zcx.exam.entity.Question;
import com.zcx.exam.entity.User;
import com.zcx.exam.entityResult.QuestionBackResult;
import com.zcx.exam.service.QuestionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionMapper mapper;


    @Override
    public List<Question> selectAll(Map<String, Object> map) {
        List<Question> questions =  mapper.selectAll(map);
        for (Question question:questions) {
            if("1".equals(question.getQuestionStatus())){
                question.setQuestionStatus("未解决");
            }else{
                question.setQuestionStatus("已解决");
            }
            if("0".equals(question.getQuestionType())){
                question.setQuestionType("产品建议");
            }
            if ("1".equals(question.getQuestionType())){
                question.setQuestionType("功能异常");
            }
            if ("2".equals(question.getQuestionType())){
                question.setQuestionType("体验不佳");
            }
            if ("3".equals(question.getQuestionType())){
                question.setQuestionType("其他");
            }

        }
        return questions;
    }

    @Override
    public void updateStatus(int id) {
        mapper.updateStatus(id);
    }

    @Override
    public QuestionBackResult selectByOne(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void addQuestionPaper(Question question) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        question.setCreateBy(user.getUsername());
        question.setCreateTime(new Date());
        question.setIsDel("0");
        question.setQuestionStatus("1");
        mapper.insertSelective(question);
    }

}
