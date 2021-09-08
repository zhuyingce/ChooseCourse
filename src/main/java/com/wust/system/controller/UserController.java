package com.wust.system.controller;

import com.wust.system.config.JwtConfig;
import com.wust.system.serrvice.UserService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
 * ~program: ChooseCourse
 * ~description: 用户信息接口
 * ~author: zhuyingce
 * ~create: 2021-08-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private JwtConfig jwtConfig;

    @Autowired
    private UserService service;

    @GetMapping("/getInfo")
    public Result getInfo(@RequestHeader("token") String token) {

        return service.getInfo(token);
    }

    @GetMapping("/test/{id}")
    public String getInfo(@PathVariable("id") Integer id) {

        return jwtConfig.createToken(id + "");
    }

    @PostMapping("/test")
    public String getId(@RequestParam("email") String email) {

        return email;
    }

}
