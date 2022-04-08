package com.wust.system.serrvice.impl;

import com.wust.system.config.JwtConfig;
import com.wust.system.dao.CourseDao;
import com.wust.system.dao.StuCouDao;
import com.wust.system.dao.TeaCouDao;
import com.wust.system.dao.TeacherDao;
import com.wust.system.entity.StuCou;
import com.wust.system.entity.Student;
import com.wust.system.entity.TeaCou;
import com.wust.system.serrvice.TeaCouService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-20
 */
@Service
@Transactional
public class TeaCouImpl implements TeaCouService {

    @Autowired
    private TeaCouDao teaCouDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StuCouDao stuCouDao;

    @Override
    public Result getLike(String content) {

        List<TeaCou> courses = teaCouDao.queryLike(content);

        return Result.success("获取成功",courses);
    }

    @Override
    public Result add(TeaCou request, String token) {

        //判断课程是否存在
        Integer couId = courseDao.queryIdByName(request.getName());

        if (couId == null) {

            return Result.error("请检查课程名称");
        }

        //解析token
        Integer userId = jwtConfig.getIdFromToken(token);

        Integer teaId = teacherDao.queryIdByUserId(userId);


        //设置教师id和课程Id
        request.setTeacherId(teaId);
        request.setCourseId(couId);

        //检查是否重复添加
        if (teaCouDao.queryByTeaCou(request) != null) {

            return Result.error("你已添加过该课程，请勿重复添加");
        }

        teaCouDao.insert(request);

        return Result.success("添加成功");
    }

    @Override
    public Result teach(String token) {

        //解析token
        Integer userId = jwtConfig.getIdFromToken(token);

        Integer teaId = teacherDao.queryIdByUserId(userId);

        List<TeaCou> courses = teaCouDao.queryByTeaId(teaId);

        return Result.success("获取成功", courses);
    }

    @Override
    public Result delete(Integer courseId, String token) {

        //解析token
        Integer userId = jwtConfig.getIdFromToken(token);

        Integer teaId = teacherDao.queryIdByUserId(userId);

        //删除teaCou项
        teaCouDao.delete(teaId, courseId);
        //删除stuCou项
        stuCouDao.deleteByteacher(teaId, courseId);

        return Result.success("删除成功");
    }

    @Override
    public Result getDetails(Integer courseId, String content, String token) {

        //解析token
        Integer userId = jwtConfig.getIdFromToken(token);

        //获取teaId
        Integer teaId = teacherDao.queryIdByUserId(userId);

        List<Student> details = stuCouDao.queryByTeacher(teaId, courseId, content);

        return Result.success("获取成功", details);
    }

    @Override
    public Result updateScore(Student request) {

        Integer affects = stuCouDao.updateScore(request);

        if (affects != 0) {
            return Result.success("修改成功");
        }

        return Result.error("修改失败");
    }
}
