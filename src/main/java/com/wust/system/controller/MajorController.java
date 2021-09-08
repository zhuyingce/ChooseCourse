package com.wust.system.controller;

import com.wust.system.serrvice.MajorService;
import com.wust.system.util.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/*
 * ~program: ChooseCourse
 * ~description: 专业
 * ~author: zhuyingce
 * ~create: 2021-08-16
 */
@RestController
@RequestMapping("/major")
@Validated
public class MajorController {

    @Autowired
    private MajorService service;

    @GetMapping("/getByDeptId")
    Result getByDeptId(@NotNull(message = "请选择院系") Integer departmentId) {

        return service.getByDeptId(departmentId);
    }
}
