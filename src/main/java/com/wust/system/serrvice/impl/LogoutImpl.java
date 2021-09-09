package com.wust.system.serrvice.impl;

import com.wust.system.config.JwtConfig;
import com.wust.system.dao.UserTokenDao;
import com.wust.system.entity.UserToken;
import com.wust.system.serrvice.LogoutService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-09-09
 */
@Service
public class LogoutImpl implements LogoutService {

    @Autowired
    private UserTokenDao userTokenDao;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Result logout(String token) {

        Integer userId = jwtConfig.getIdFromToken(token);

        userTokenDao.deleteToken(userId);

        return Result.success("退出成功");
    }
}
