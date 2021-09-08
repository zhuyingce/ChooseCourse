package com.wust.system.dao;

import com.wust.system.entity.Major;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * ~program: ChooseCourse
 * ~description: 专业
 * ~author: zhuyingce
 * ~create: 2021-08-16
 */
@Repository
public interface MajorDao {
    /*
     * ~Description: 由deptId获取信息
     * ~Param: [departmentId]
     * ~return: [Major]
     * ~Author: zhuyingce
     * ~Date: 2021/8/16
     */
    @Select("select * from major where departmentId = #{deptId}")
    List<Major> queryByDeptId(@Param("deptId") Integer deptId);

}
