package com.wust.system.serrvice;

import com.wust.system.entity.StuCou;
import com.wust.system.entity.Student;
import com.wust.system.entity.TeaCou;
import com.wust.system.util.Result;

/*
 * ~program: ChooseCourse
 * ~description: 教师授课
 * ~author: zhuyingce
 * ~create: 2021-08-20
 */
public interface TeaCouService {

    Result getLike(String content);

    Result add(TeaCou request, String token);

    Result teach(String token);

    Result delete(Integer courseId, String token);

    Result getDetails(Integer courseId, String content, String token);

    Result updateScore(Student request);

}
