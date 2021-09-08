package com.wust.system.dao;

import com.wust.system.entity.TeaCou;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * ~program: ChooseCourse
 * ~description: 教师授课
 * ~author: zhuyingce
 * ~create: 2021-08-20
 */
@Repository
public interface TeaCouDao {

    /*
     * ~Description: 添加一行
     * ~Param: [TeaCou]
     * ~return: [void]
     * ~Author: zhuyingce
     * ~Date: 2021/8/20
     */
    @Insert("insert into teaCou(teacherId,courseId,classroom,className,week) " +
            "values(#{teacherId},#{courseId},#{classroom},#{className},#{week})")
    void insert(TeaCou teaCou);

    /*
     * ~Description: 根据teaId和couId删除一行
     * ~Param: [teaId,couId]
     * ~return: [void]
     * ~Author: zhuyingce
     * ~Date: 2021/8/20
     */
    @Delete("delete from teaCou where teacherId = #{teaId} and courseId = #{couId}")
    void delete(@Param("teaId") Integer teaId, @Param("couId") Integer couId);

    /*
     * ~Description: 模糊查询(根据课程名或教师名)
     * ~Param: [searchContent]
     * ~return: [List<Course>]
     * ~Author: zhuyingce
     * ~Date: 2021/8/19
     */
    @Select("select * from (select course.*,teacher.name as teacher,teacher.id as teacherId," +
            "teaCou.classroom,teaCou.className,teaCou.week " +
            "from course,teacher,teaCou " +
            "where teacher.id = teaCou.teacherId and course.id = teaCou.courseId) as result " +
            "where result.name like concat('%',#{con},'%') " +
            "or result.teacher like concat('%',#{con},'%') " +
            " order by result.name")
    List<TeaCou> queryLike(@Param("con") String content);

    /*
     * ~Description: 检查是否重复添加
     * ~Param: [TeaCou]
     * ~return: [Integer]
     * ~Author: zhuyingce
     * ~Date: 2021/8/20
     */
    @Select("select id from teaCou where teacherId = #{teacherId} and courseId = #{courseId} " +
            "and classroom = #{classroom} and className = #{className} and week = #{week}")
    Integer queryByTeaCou(TeaCou teaCou);
    
    /*
    * ~Description: 根据教师id查询自己所教课程
    * ~Param: [teaId]
    * ~return: [List<TeaCou>]
    * ~Author: zhuyingce
    * ~Date: 2021/8/20
    */
    @Select("select course.id,course.name,course.credit,course.classHour,teaCou.classroom," +
            "teaCou.className,teaCou.week,(select count(id) from stuCou " +
            "where courseId = course.id and teacherId = teacher.id) as stuNum " +
            "from course,teacher,teaCou where teacher.id = teaCou.teacherId " +
            "and teaCou.courseId = course.id and teacher.id = #{teaId}")
    List<TeaCou> queryByTeaId(@Param("teaId") Integer teaId);
}
