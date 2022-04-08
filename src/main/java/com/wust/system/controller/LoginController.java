package com.wust.system.controller;

import com.wust.system.pojo.Login;
import com.wust.system.serrvice.LoginService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
 * ~program: ChooseCourse
 * ~description: 用户接口
 * ~author: zhuyingce
 * ~create: 2021-08-13
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public Result login(@RequestBody @Validated Login request, HttpServletResponse response) {

        return service.login(request, response);
    }

}
