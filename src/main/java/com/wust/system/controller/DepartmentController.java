package com.wust.system.controller;

import com.wust.system.serrvice.DepartmentService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * ~program: ChooseCourse
 * ~description: 院系
 * ~author: zhuyingce
 * ~create: 2021-08-16
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/getAll")
    public Result getAll() {

        return service.getAll();
    }

    @GetMapping("/test/{id}")
    public Result test(@PathVariable("id") Integer id) {

        System.out.println(id);

        return Result.success("成功", null);
    }
}
