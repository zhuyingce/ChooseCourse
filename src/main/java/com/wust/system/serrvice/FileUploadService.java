package com.wust.system.serrvice;

import com.wust.system.util.Result;
import org.springframework.web.multipart.MultipartFile;

/*
 * ~program: ChooseCourse
 * ~description: 文件上传
 * ~author: zhuyingce
 * ~create: 2021-09-21
 */
public interface FileUploadService {

    Result uploadHeadPortrait(MultipartFile file, String token);
}
