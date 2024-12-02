package com.zcx.exam.service.impl;

import com.zcx.exam.dao.UserMapper;
import com.zcx.exam.entity.User;
import com.zcx.exam.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper mapper;

    @Transactional(readOnly = true)
    public User selectByName(String username) {

        return mapper.selectByName(username);
    }

    public String queryUserStatus(String username){

        return mapper.queryUserStatus(username);
    }
}
