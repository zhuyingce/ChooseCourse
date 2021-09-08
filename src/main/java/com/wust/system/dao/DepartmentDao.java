package com.wust.system.dao;

import com.wust.system.entity.Department;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * ~program: ChooseCourse
 * ~description: 院系
 * ~author: zhuyingce
 * ~create: 2021-08-16
 */
@Repository
public interface DepartmentDao {
    /*
     * ~Description: 获取所有院系信息
     * ~Param: 无
     * ~return: [List<Department>]
     * ~Author: zhuyingce
     * ~Date: 2021/8/16
     */
    @Select("select * from department")
    List<Department> query();
}
