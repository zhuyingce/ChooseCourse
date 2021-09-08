package com.wust.system.serrvice.impl;

import com.wust.system.config.JwtConfig;
import com.wust.system.dao.*;
import com.wust.system.entity.StuCou;
import com.wust.system.entity.Student;
import com.wust.system.entity.User;
import com.wust.system.serrvice.StuCouService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * ~program: ChooseCourse
 * ~description: 选课
 * ~author: zhuyingce
 * ~create: 2021-08-19
 */
@Service
@Transactional
public class StuCouImpl implements StuCouService {

    @Autowired
    private StuCouDao stuCouDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Result add(Integer courseId, Integer teacherId, String token) {

        if (courseDao.queryById(courseId) == null) {
            return Result.error("课程不存在");
        }

        //解析token
        Integer userId = jwtConfig.getIdFromToken(token);

        Integer stuId = studentDao.queryIdByUserId(userId);

        //是否已选过
        if (stuCouDao.queryItem(stuId, courseId) != null) {

            return Result.error("请勿重复选择相同课程");
        }

        stuCouDao.insert(userId, courseId, teacherId);

        return Result.success("选课成功");
    }

    @Override
    public Result delete(Integer courseId, String token) {

        //解析token
        Integer userId = jwtConfig.getIdFromToken(token);

        Integer stuId = studentDao.queryIdByUserId(userId);

        Integer infects = stuCouDao.delete(stuId, courseId);

        if (infects != 0) {

            return Result.success("退选成功");
        }

        return Result.error("该门课已不能退选");
    }

    @Override
    public Result getLike(String token, String content) {

        //解析token
        Integer userId = jwtConfig.getIdFromToken(token);

        //获取stuId
        Integer stuId = studentDao.queryIdByUserId(userId);

        List<StuCou> courses = stuCouDao.queryLikeByStuId(stuId, content);

        return Result.success("获取成功", courses);
    }
}
