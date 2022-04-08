package com.wust.system.dao;

import com.wust.system.entity.StuCou;
import com.wust.system.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * ~program: ChooseCourse
 * ~description: 选课
 * ~author: zhuyingce
 * ~create: 2021-08-19
 */
@Repository
public interface StuCouDao {
    /*
     * ~Description: 新增一行
     * ~Param: [studentId,courseId]
     * ~return: [void]
     * ~Author: zhuyingce
     * ~Date: 2021/8/19
     */
    @Insert("insert into stuCou(studentId,courseId,teacherId) values(#{stuId},#{couId},#{teaId})")
    void insert(@Param("stuId") Integer stuId, @Param("couId") Integer couId, @Param("teaId") Integer teaId);

    /*
     * ~Description: 根据teaId和couId删除一行
     * ~Param: [teaId,couId]
     * ~return: [void]
     * ~Author: zhuyingce
     * ~Date: 2021/8/20
     */
    @Delete("delete from stuCou where teacherId = #{teaId} and courseId = #{couId}")
    void deleteByteacher(@Param("teaId") Integer teaId, @Param("couId") Integer couId);

    /*
     * ~Description: 退选课程
     * ~Param: [stuId,couId]
     * ~return: [void]
     * ~Author: zhuyingce
     * ~Date: 2021/8/19
     */
    @Delete("delete from stuCou " +
            "where studentId = #{stuId} and courseId = #{couId} " +
            "and gpa = 0.0 and score = 0.0")
    Integer delete(@Param("stuId") Integer stuId, @Param("couId") Integer couId);

    /*
     * ~Description: 修改学生分数
     * ~Param: [stuId,couId]
     * ~return: [void]
     * ~Author: zhuyingce
     * ~Date: 2021/8/23
     */
    @Update("update stuCou set score = #{score},gpa = #{gpa} " +
            "where studentId = #{id} and courseId = #{courseId}")
    Integer updateScore(Student student);

    /*
     * ~Description: 判断课程是否已选过该课程
     * ~Param: [studentId,courseId]
     * ~return: [Integer]
     * ~Author: zhuyingce
     * ~Date: 2021/8/19
     */
    @Select("select id from stuCou where studentId = #{stuId} and courseId = #{couId}")
    Integer queryItem(@Param("stuId") Integer stuId, @Param("couId") Integer couId);

    /*
     * ~Description: 已选课程
     * ~Param: [userId]
     * ~return: [List<Course>]
     * ~Author: zhuyingce
     * ~Date: 2021/8/19
     */
    @Select("select * from (select course.*,teacher.name as teacher," +
            "stuCou.score,stuCou.gpa,teaCou.classroom,teaCou.week " +
            "from course,teacher,teaCou,student,stuCou " +
            "where teacher.id = teaCou.teacherId and course.id = teaCou.courseId " +
            "and student.id = stuCou.studentId and stuCou.courseId=course.id " +
            "and stuCou.teacherId = teacher.id " +
            "and student.id = #{stuId}) as result " +
            "where result.name like concat('%',#{con},'%') " +
            "or result.teacher like concat('%',#{con},'%') " +
            "order by result.name")
    List<StuCou> queryLikeByStuId(@Param("stuId") Integer stuId, @Param("con") String content);

    /*
     * ~Description: 教师查看某课程选课情况
     * ~Param: [teaId,couId]
     * ~return: [List<Student>]
     * ~Author: zhuyingce
     * ~Date: 2021/8/21
     */
    @Select("select * from (select student.id,student.name,student.sex,department.name as department," +
            "major.name as major,course.name as courseName,student.grade,stuCou.score,stuCou.gpa " +
            "from student,department,major,stuCou,teacou,course where stuCou.courseId = course.id " +
            "and teaCou.courseId = course.id and student.id = stuCou.studentId and " +
            "student.departmentId = department.id and student.majorId = major.id and " +
            "stuCou.teacherId = teaCou.teacherId and teaCou.teacherId = #{teaId} and " +
            "course.id = #{couId}) as result " +
            "where result.name like concat('%',#{con},'%') " +
            "or result.major like concat('%',#{con},'%') " +
            "order by result.name")
    List<Student> queryByTeacher(@Param("teaId") Integer teaId,
                                 @Param("couId") Integer couId,
                                 @Param("con") String content);
}
