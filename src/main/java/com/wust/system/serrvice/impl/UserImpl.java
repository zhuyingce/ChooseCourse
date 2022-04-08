package com.wust.system.serrvice.impl;

import com.wust.system.config.JwtConfig;
import com.wust.system.dao.StudentDao;
import com.wust.system.dao.TeacherDao;
import com.wust.system.dao.UserDao;
import com.wust.system.serrvice.UserService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-17
 */
@Service
@Transactional
public class UserImpl implements UserService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Result getInfo(String token) {

        //解析token
        Integer userId = jwtConfig.getIdFromToken(token);

        String type = userDao.queryTypeByUserId(userId);

        switch (type) {

            case "student":
                return Result.success("获取成功", studentDao.queryInfo(userId));

            case "teacher":
                return Result.success("获取成功", teacherDao.queryInfo(userId));

            default:
                return Result.error("身份不明");
        }
    }
}
