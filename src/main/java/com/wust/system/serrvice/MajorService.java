package com.wust.system.serrvice;

import com.wust.system.util.Result;

/*
 * ~program: ChooseCourse
 * ~description: 专业
 * ~author: zhuyingce
 * ~create: 2021-08-16
 */
public interface MajorService {
    Result getByDeptId(Integer deptId);
}
