package com.wust.system.serrvice;

import com.wust.system.pojo.Login;
import com.wust.system.util.Result;

import javax.servlet.http.HttpServletResponse;

/*
 * ~program: ChooseCourse
 * ~description: 登录
 * ~author: zhuyingce
 * ~create: 2021-08-13
 */
public interface LoginService {
    Result login(Login user, HttpServletResponse response);
}
