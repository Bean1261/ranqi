package com.weichai.ranqi.controllers;

import com.weichai.ranqi.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @RequestMapping(value = "/api/test", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        // 模拟处理逻辑
        System.out.println(user.getId());
        return "用户已创建：姓名 = "+ user.getName();
    }
}
