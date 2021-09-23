package com.wust.system.serrvice.impl;

import com.wust.system.config.JwtConfig;
import com.wust.system.dao.UserDao;
import com.wust.system.serrvice.FileUploadService;
import com.wust.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-09-21
 */

@Service
@Transactional
public class FileUploadImpl implements FileUploadService {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserDao userDao;

    @Override
    public Result uploadHeadPortrait(MultipartFile file, String token) {

        if (file.isEmpty()) {

            return Result.error("文件不能为空");
        }

        //获取文件名
        String originalFilename = file.getOriginalFilename();

        if (originalFilename != null) {

            if (originalFilename.endsWith(".jpg")) {
                //更改文件名
                Integer id = jwtConfig.getIdFromToken(token);
                String username = userDao.queryUsernameById(id);
                String fileName = username + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

                //指定存储路径
                String filePath = "E:\\javacode\\spring\\ChooseCourse\\src\\main\\resources\\static\\";

                //定义文件
                File dest = new File(filePath + fileName);

                //上传服务器
                if (!dest.getParentFile().exists()) {

                    boolean mkdirs = dest.getParentFile().mkdirs();
                }

                //拷贝文件
                try {

                    file.transferTo(dest);
                } catch (IOException e) {

                    return Result.error("上传失败");
                }

            } else {

                return Result.error("请选择正确文件格式");
            }
        }


        return Result.success("上传成功");
    }
}
