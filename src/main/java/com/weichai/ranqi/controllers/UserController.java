package com.weichai.ranqi.controllers;

import com.weichai.ranqi.entity.User;
import com.weichai.ranqi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 获取所有用户
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 根据 ID 获取用户
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 添加新用户
    @PostMapping
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "用户添加成功！";
    }

    // 更新用户信息
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return "用户更新成功！";
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "用户删除成功！";
    }
}
