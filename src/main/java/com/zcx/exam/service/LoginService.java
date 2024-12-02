package com.zcx.exam.service;


import com.zcx.exam.entity.User;

public interface LoginService {

    User selectByName(String user);

    String queryUserStatus(String username);
}
