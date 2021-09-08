package com.wust.system.controller;

import com.wust.system.entity.StuCou;
import com.wust.system.entity.Student;
import com.wust.system.entity.TeaCou;
import com.wust.system.serrvice.TeaCouService;
import com.wust.system.util.Result;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PositiveOrZero;

/*
 * ~program: ChooseCourse
 * ~description: 教师授课
 * ~author: zhuyingce
 * ~create: 2021-08-20
 */
@RestController
@RequestMapping("/tea_cou")
public class TeaCouController {

    @Autowired
    private TeaCouService service;

    //备选课程
    @GetMapping("/getLike")
    public Result getLike(String content) {

        return service.getLike(content);
    }

    //添加课程
    @PostMapping("/add")
    public Result add(@RequestBody @Validated TeaCou request, @RequestHeader("token") String token) {

        return service.add(request, token);
    }

    //我教的课
    @GetMapping("/teach")
    public Result teach(@RequestHeader("token") String token) {

        return service.teach(token);
    }

    //删除课程
    @DeleteMapping("/delete/{courseId}")
    public Result delete(@PathVariable("courseId") Integer courseId, @RequestHeader("token") String token) {

        return service.delete(courseId, token);
    }

    //教师查看某门课选课情况
    @GetMapping("/getDetails")
    public Result getDetails(Integer courseId, String content, @RequestHeader("token") String token) {

        return service.getDetails(courseId, content, token);
    }

    //教师打分
    @PutMapping("/updateScore")
    public Result updateScore(@RequestBody @Validated Student request) {

        return service.updateScore(request);
    }
}
