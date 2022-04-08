package com.wust.system.controller;

import com.wust.system.entity.User;
import com.wust.system.serrvice.RegisterService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ~program: ChooseCourse
 * ~description: 注册
 * ~author: zhuyingce
 * ~create: 2021-08-14
 */
@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping("/register")
    public Result register(@RequestBody @Validated User request) {

        return service.register(request);
    }
}
