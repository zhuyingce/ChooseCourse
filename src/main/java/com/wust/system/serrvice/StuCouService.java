package com.wust.system.serrvice;

import com.wust.system.util.Result;

/*
 * ~program: ChooseCourse
 * ~description: 选课
 * ~author: zhuyingce
 * ~create: 2021-08-19
 */
public interface StuCouService {

    Result add(Integer courseId, Integer teacherId, String token);

    Result delete(Integer courseId, String token);

    Result getLike(String token,String content);
}
