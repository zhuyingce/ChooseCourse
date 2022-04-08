package com.wust.system.dao;

import com.wust.system.entity.Course;
import com.wust.system.entity.StuCou;
import com.wust.system.entity.TeaCou;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * ~program: ChooseCourse
 * ~description:课程
 * ~author: zhuyingce
 * ~create: 2021-08-18
 */
@Repository
public interface CourseDao {

    /*
     * ~Description: 判断courseId是否正确
     * ~Param: [courseId]
     * ~return: [Integer]
     * ~Author: zhuyingce
     * ~Date: 2021/8/19
     */
    @Select("select id from course where id = #{couId}")
    Integer queryById(@Param("couId") Integer courseId);

    /*
     * ~Description: 通过课程名判断课程是否存在
     * ~Param: [name]
     * ~return: [Integer]
     * ~Author: zhuyingce
     * ~Date: 2021/8/20
     */
    @Select("select id from course where name = #{name}")
    Integer queryIdByName(@Param("name") String name);

}
