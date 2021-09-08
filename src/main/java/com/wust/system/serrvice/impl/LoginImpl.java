package com.wust.system.serrvice.impl;

import com.wust.system.config.JwtConfig;
import com.wust.system.dao.StudentDao;
import com.wust.system.dao.TeacherDao;
import com.wust.system.dao.UserDao;
import com.wust.system.dao.UserTokenDao;
import com.wust.system.entity.Student;
import com.wust.system.entity.Teacher;
import com.wust.system.entity.UserToken;
import com.wust.system.pojo.Login;
import com.wust.system.serrvice.LoginService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-13
 */
@Service
@Transactional
public class LoginImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public Result login(Login request) {

        //判断登录情况
        Integer userId = userDao.queryLogin(request);

        //失败
        if (userId == null) {
            return Result.error("用户名或密码错误");
        }
        //成功返回token,姓名和学号
        String token = jwtConfig.createToken(userId+"");
        String name = "";
        String number = "";

        //判断身份
        String type = request.getType();

        switch (type) {
            case "student" :
                name = studentDao.queryNameByUserId(userId);
                number = studentDao.queryNumByUserId(userId);
                break;

            case "teacher" :
                name = teacherDao.queryNameByUserId(userId);
                number = teacherDao.queryNumByUserId(userId);
                break;

            default:
                return Result.error("身份不明");
        }

        //生成UserToken
        UserToken userToken = this.getUserToken(token, userId);

        //将token存入数据库，后面用于退出登录
        this.storeToken(userId, userToken);

        //登录成功返回token,name,number
        LinkedHashMap<String, String> response = new LinkedHashMap<>();
        response.put("token", token);
        response.put("name", name);
        response.put("number", number);

        return Result.success("登录成功", response);
    }

    //封装UserToken
    private UserToken getUserToken(String token,Integer userId) {
        //封装UserToken
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUserId(userId);

        return userToken;
    }

    //存储token
    private void storeToken(Integer userId,UserToken userToken) {
        if (userTokenDao.queryIdByUserId(userId) == null) {
            //如果是第一次登录，新增一行
            userTokenDao.insertToken(userToken);
        } else {
            //如果不是第一次登录则修改token
            userTokenDao.updateToken(userToken);
        }
    }
}
