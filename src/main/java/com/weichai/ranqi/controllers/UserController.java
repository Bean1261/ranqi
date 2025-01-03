package com.weichai.ranqi.controllers;

import com.weichai.ranqi.entity.User;
import com.weichai.ranqi.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://192.168.205.6:9526", allowCredentials = "true") // 允许跨域
public class UserController {


    @Autowired
    private UserService userService;

    private static final String MOCK_TOKEN = "mock-token";

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        // 根据用户名查询数据库
        User dbUser = userService.getUserById(user.getId());
        Map<String, Object> response = new HashMap<>();

        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            response.put("token", MOCK_TOKEN); // 模拟 token 返回
            response.put("user", dbUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "用户名或密码错误");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getInfo(@RequestParam String token) {
        Map<String, Object> response = new HashMap<>();
        if (MOCK_TOKEN.equals(token)) {
            response.put("roles", new String[]{"admin"}); // 返回模拟角色
            response.put("name", "admin");
            response.put("avatar", "https://example.com/avatar.jpg");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "无效的 Token");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("退出成功", HttpStatus.OK);
    }
    @ApiOperation("获取所有用户")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);  // 返回用户列表
    }

    @ApiOperation("根据 ID 获取用户")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 用户未找到
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation("添加新用户")
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user.getName() + " 用户添加成功！", HttpStatus.CREATED);  // 返回201
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        boolean updated = userService.updateUser(id, user);
        if (!updated) {
            return new ResponseEntity<>("用户更新失败，用户ID可能不存在", HttpStatus.NOT_FOUND);  // 404
        }
        return new ResponseEntity<>("用户更新成功！", HttpStatus.OK);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return new ResponseEntity<>("用户删除失败，用户ID不存在", HttpStatus.NOT_FOUND);  // 404
        }
        return new ResponseEntity<>("用户删除成功！", HttpStatus.OK);
    }
}
