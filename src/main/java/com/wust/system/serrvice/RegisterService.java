package com.wust.system.serrvice;

import com.wust.system.entity.User;
import com.wust.system.util.Result;

/*
 * ~program: ChooseCourse
 * ~description: 注册
 * ~author: zhuyingce
 * ~create: 2021-08-14
 */
public interface RegisterService {

    Result register(User user);
}
