package com.wust.system.controller;

import com.wust.system.serrvice.LogoutService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ~program: ChooseCourse
 * ~description: 退出登录
 * ~author: zhuyingce
 * ~create: 2021-09-09
 */
@RestController
@RequestMapping("/user")
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @PutMapping("/logout")
    public Result logout(@RequestHeader("token") String token) {

        return logoutService.logout(token);
    }
}
