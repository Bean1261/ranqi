package com.weichai.ranqi.service.impl;

import com.weichai.ranqi.entity.User;
import com.weichai.ranqi.mapper.UserMapper;
import com.weichai.ranqi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        // 从数据库中查询所有用户
        return userMapper.selectAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        // 从数据库中根据 ID 查询用户
        return userMapper.selectUserById(id);
    }

    @Override
    public void addUser(User user) {
        // 插入用户到数据库
        userMapper.insertUser(user);
        // 插入后，打印生成的 ID
        System.out.println("Inserted User ID: " + user.getId());
    }

    @Override
    public boolean updateUser(Long id, User updatedUser) {
        // 更新用户信息
        updatedUser.setId(id); // 设置更新的用户 ID
        userMapper.updateUser(updatedUser);
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        // 根据 ID 删除用户
        userMapper.deleteUserById(id);
        return false;
    }
}
