package com.wust.system.controller;

import com.wust.system.serrvice.StuCouService;
import com.wust.system.util.Result;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/*
 * ~program: ChooseCourse
 * ~description: 学生选课
 * ~author: zhuyingce
 * ~create: 2021-08-19
 */
@Validated
@RestController
@RequestMapping("/stu_cou")
public class StuCouController {

    @Autowired
    private StuCouService service;

    //选课
    @PostMapping("/add")
    public Result add(@NotNull(message = "课程id不能为空") Integer courseId,
                      @NotNull(message = "教师id不能为空") Integer teacherId,
                      @RequestHeader("token") String token) {

        return service.add(courseId, teacherId, token);
    }

    //退选
    @DeleteMapping("/delete/{courseId}")
    public Result delete(@PathVariable("courseId") Integer courseId, @RequestHeader("token") String token) {

        return service.delete(courseId, token);
    }

    //已选课程
    @GetMapping("/getLike")
    public Result getLike(@RequestHeader("token") String token, String content) {

        return service.getLike(token, content);
    }
}
