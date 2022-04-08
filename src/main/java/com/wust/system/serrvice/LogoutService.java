package com.wust.system.serrvice;

import com.wust.system.util.Result;

/*
 * ~program: ChooseCourse
 * ~description: 退出登录
 * ~author: zhuyingce
 * ~create: 2021-09-09
 */
public interface LogoutService {

    Result logout(String token);

}
