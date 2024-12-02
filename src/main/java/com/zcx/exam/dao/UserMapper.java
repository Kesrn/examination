package com.zcx.exam.dao;

import com.zcx.exam.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByName(String name);

    List<User> selectAll(Map<String, Object> map);

    String queryUserStatus(String username);

    Integer findByNmeCount(String mobile);
}