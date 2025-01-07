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
    public User getUserByPhoneOrUsername(String phone, String username) {
        if (phone != null) {
            User userByPhone = userMapper.getUserByPhone(phone);
            if (userByPhone != null) {
                return userByPhone;
            }
        }
        if (username != null) {
            return userMapper.getUserByUsername(username);
        }
        return null; // 如果都未找到，返回 null
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
        int rowsAffected = userMapper.updateUser(updatedUser); // 执行更新操作
        return rowsAffected > 0; // 如果受影响的行数大于0，表示更新成功
    }


    @Override
    public boolean deleteUser(Long id) {
        User user = userMapper.selectUserById(id);
        if (user == null) {
            return false;  // 用户不存在
        }
        return userMapper.deleteUserById(id) > 0;  // 删除成功返回 true
    }


    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
