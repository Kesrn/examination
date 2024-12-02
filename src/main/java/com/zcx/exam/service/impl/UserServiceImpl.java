package com.zcx.exam.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.zcx.exam.Exception.BusinessException;
import com.zcx.exam.common.UserEnum;
import com.zcx.exam.dao.UserMapper;
import com.zcx.exam.entity.User;
import com.zcx.exam.service.UserService;
import com.zcx.exam.utils.EncryptUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService     {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll(Map<String,Object> map) {
        List<User> users = userMapper.selectAll(map);
        for (User user:users) {
            if("0".equals(user.getSex())){
                user.setSex("男");
            }else{
                user.setSex("女");
            }
            user.setMobile(EncryptUtils.mobileEncrypt(user.getMobile()));
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        String md5 = "MD5";
        String _salt = ByteSource.Util.bytes(user.getUsername()).toString();
        Object salt = ByteSource.Util.bytes(_salt);
        int hasIter = 1024;

        Object result = new SimpleHash(md5, "123456", salt, hasIter);
        user.setPassword(result.toString());
        user.setSalt(_salt);
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();
        user.setCreateDate(new Date());
        user.setCreateBy(user1.getUsername());
        user.setUpdateDate(new Date());
        user.setUpdateBy(user1.getUsername());
        user.setStatus("0");
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        User admin = (User) SecurityUtils.getSubject().getPrincipal();
        user.setUpdateBy(admin.getUsername());
        user.setUpdateDate(new Date());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteUser(int id) {
        Subject subject = SecurityUtils.getSubject();
        User loginUser = (User) subject.getPrincipal();
        User user = findOne(id);
        //如果当前登录人是删除的用户或者删除的用户是管理员
        if(loginUser.getId().equals(user.getId()) || UserEnum.ADMIN_ROLE.getDescription().equals(user.getRole())){
            throw new BusinessException("系统异常，请检查当前用户信息！");
        }
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User findOne(Integer id) {
       User user =  userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public void initUser(User user) {
        String md5 = "MD5";
        String _salt = ByteSource.Util.bytes(user.getUsername()).toString();
        Object salt = ByteSource.Util.bytes(_salt);
        int hasIter = 1024;

        Object result = new SimpleHash(md5, user.getPassword(), salt, hasIter);
        user.setPassword(result.toString());
        user.setSalt(_salt);
        user.setRole("user");
        user.setSex("1");
        user.setCreateDate(new Date());
        user.setCreateBy(user.getUsername());
        user.setUpdateDate(new Date());
        user.setUpdateBy(user.getUsername());
        user.setStatus("0");
        userMapper.insert(user);
    }

    @Override
    public Integer findByNameCount(String mobile) {
        return userMapper.findByNmeCount(mobile);
    }

    @Override
    public void resetPassword(Integer id) {
        User user = findOne(id);
        String md5 = "MD5";
        String _salt = ByteSource.Util.bytes(user.getUsername()).toString();
        Object salt = ByteSource.Util.bytes(_salt);
        int hasIter = 1024;

        Object result = new SimpleHash(md5, "123456", salt, hasIter);
        user.setPassword(result.toString());
        user.setSalt(_salt);
        userMapper.updateByPrimaryKey(user);
    }
}
