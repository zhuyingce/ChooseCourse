package com.wust.system.serrvice.impl;

import com.wust.system.dao.StudentDao;
import com.wust.system.dao.TeacherDao;
import com.wust.system.dao.UserDao;
import com.wust.system.entity.User;
import com.wust.system.serrvice.RegisterService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-14
 */
@Service
@Transactional
public class RegisterImpl implements RegisterService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Result register(User request) {

        //获取用户名
        Integer userId = userDao.queryIdByUsername(request.getUsername());

        if (userId != null) {
            return Result.error("用户名已存在", null);
        }

        //获取身份
        String type = request.getType();
        //插入数据
        userDao.insert(request);
        //更新userId
        userId = userDao.queryIdByUsername(request.getUsername());

        switch (type) {
            case "student":
                //学生表新增一行
                studentDao.insertUserId(userId);
                //加入详细信息
                studentDao.updateByUserId(request,userId);
                break;

            case "teacher":
                //老师新增一行
                teacherDao.insertUserId(userId);
                //加入详细信息
                teacherDao.updateByUserId(request, userId);
                break;

            default:
                return Result.error("身份不明");
        }

        return Result.success("注册成功");
    }
}
