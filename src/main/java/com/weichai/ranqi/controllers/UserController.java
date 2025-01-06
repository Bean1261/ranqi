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

        String token = JwtUtils.generateToken(user.getusername());
        return Result.ok().data("token", token);
    }


    @GetMapping("/info")
    public Result info(String token){
        String username = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80";
        return Result.ok().data("name",username).data("avatar", url);
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
