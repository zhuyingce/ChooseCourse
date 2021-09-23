package com.wust.system.controller;

import com.wust.system.serrvice.FileUploadService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/*
 * ~program: ChooseCourse
 * ~description: 文件上传
 * ~author: zhuyingce
 * ~create: 2021-09-21
 */
@RestController
@RequestMapping("/file")
public class FileUpLoadController {

    @Autowired
    private FileUploadService fileUploadService;

    //上传头像
    @PostMapping("/headPortrait")
    public Result uploadHeadPortrait(MultipartFile file, @RequestHeader("token") String token) {

        return fileUploadService.uploadHeadPortrait(file, token);
    }
}
