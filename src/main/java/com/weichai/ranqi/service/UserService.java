// UserService 接口
package com.weichai.ranqi.service;

import com.weichai.ranqi.entity.User;

import java.util.List;

public interface UserService {

    // 获取所有用户
    List<User> getAllUsers();

    // 根据 ID 获取用户
    User getUserById(Long id);

    // 添加新用户
    void addUser(User user);

    // 更新用户信息
    boolean updateUser(Long id, User user);

    // 删除用户
    boolean deleteUser(Long id);

    User getUserByUsername(String getusername);

    User getUserByPhoneOrUsername(String phone, String username);

}
