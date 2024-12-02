package com.zcx.exam.service;

import com.zcx.exam.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查询所有用户信息
     *
     * @param map 用于传递查询条件的映射表，键值对形式存储条件
     * @return 返回一个包含所有用户对象的列表
     */
    List<User> selectAll(Map<String, Object> map);

    /**
     * 添加新用户
     *
     * @param user 要添加的用户对象，包含用户的所有信息
     */
    void addUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 包含更新后信息的用户对象，通过此对象更新数据库中的用户记录
     */
    void updateUser(User user);

    /**
     * 删除指定ID的用户
     *
     * @param id 要删除用户的唯一标识符
     */
    void deleteUser(int id);

    /**
     * 根据ID查找用户
     *
     * @param id 用户的唯一标识符
     * @return 返回找到的用户对象，如果未找到则返回null
     */
    User findOne(Integer id);

    /**
     * 初始化用户信息
     *
     * @param user 需要初始化的用户对象，通常用于创建新用户时设置默认值
     */
    void initUser(User user);

    /**
     * 根据用户名查询用户数量
     *
     * @param username 要查询的用户名
     * @return 返回具有指定用户名的用户数量
     */
    Integer findByNameCount(String username);

    /**
     * 重置指定用户的密码
     *
     * @param id 需要重置密码的用户的唯一标识符
     */
    void resetPassword(Integer id);

}
