package com.weichai.ranqi.controllers;

import com.weichai.ranqi.utils.JwtUtils;
import com.weichai.ranqi.utils.Result;
import com.weichai.ranqi.entity.User;
import com.weichai.ranqi.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "https://192.168.54.6:9528", allowCredentials = "true")
public class UserController {


    @Autowired
    private UserService userService;

    private static final String MOCK_TOKEN = "mock-token";


    @PostMapping("/login")
    public Result login(@RequestBody User user){
        // 检查用户名和密码是否匹配
        if (user.getPhone() == null || user.getPassword() == null) {
            return Result.error().message("手机号或密码不能为空");
        }

        // 检查用户是否存在
        User dbUser = userService.getUserByPhone(user.getPhone());
        if (dbUser == null) {
            return Result.error().message("用户不存在");
        }

        if (dbUser == null || !dbUser.getPassword().equals(user.getPassword())) {
            return Result.error().message("电话号或密码错误");
        }

        // 生成 token
        String token = JwtUtils.generateToken(dbUser.getPhone());
        return Result.ok().data("token", token);
    }


    @GetMapping("/info")
    public Result info(String token) {
        // 从 token 中解析用户名
        String phone = JwtUtils.getClaimsByToken(token).getSubject();

        // 获取用户信息
        User user = userService.getUserByPhone(phone);
        if (user == null) {
            return Result.error().message("用户不存在");
        }

        // 设置默认头像 URL
        String url = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80";

        // 模拟角色信息
        String roles = user.getPermissions();

        // 返回结果
        return Result.ok()
                .data("phone", phone)
                .data("avatar", url)
                .data("roles", roles); // 添加 roles 字段
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }
    @ApiOperation("获取所有用户")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);  // 返回用户列表
    }

    @ApiOperation("根据 phone 或 username 查询用户")
    @GetMapping("/query")
    public ResponseEntity<User> getUserByPhoneOrUsername(@RequestParam(required = false) String phone,
                                                         @RequestParam(required = false) String username) {
        if (phone == null && username == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 如果两个参数都为空，返回 400
        }

        User user = userService.getUserByPhoneOrUsername(phone, username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 用户未找到
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
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
        return new ResponseEntity<>(user.getusername() + " 用户添加成功！", HttpStatus.CREATED);  // 返回201
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

    @ApiOperation("根据关键字模糊查询用户")
    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> searchUsers(@RequestParam String query) {
        // 调用 UserService 的方法查询匹配的用户
        List<User> users = userService.searchUsersByUsername(query);

        System.out.println("Received search query: " + query); // 打印日志
//        if (users == null || users.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 未找到用户
//        }
        // 只返回必要的字段，如 ID 和用户名
        List<Map<String, Object>> results = new ArrayList<>();
        for (User user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("phone", user.getPhone());
            userMap.put("name", user.getusername());
            results.add(userMap);
        }
//        System.out.println("Search results: " + users); // 打印结果
        return new ResponseEntity<>(results, HttpStatus.OK); // 返回匹配的用户
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
