package com.wust.system.dao;

import com.wust.system.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/*
 * ~program: ChooseCourse
 * ~description: 教师
 * ~author: zhuyingce
 * ~create: 2021-08-13
 */
@Repository
public interface TeacherDao {
    /*
     * ~Description: 插入一行userId
     * ~Param: [userId]
     * ~return: void
     * ~Author: zhuyingce
     * ~Date: 2021/8/14
     */
    @Insert("insert into teacher(userId) values(#{userId})")
    void insertUserId(@Param("userId") Integer userId);

    /*
     * ~Description: 注册时加入其他信息
     * ~Param: [Register]
     * ~return: [void]
     * ~Author: zhuyingce
     * ~Date: 2021/8/17
     */
    @Update("update teacher set name=#{reg.name},sex=#{reg.sex},number=#{reg.number}," +
            "nation=#{reg.nation},departmentId=#{reg.departmentId},position=#{reg.position}" +
            "where userId=#{userId}")
    void updateByUserId(@Param("reg") User register, @Param("userId") Integer userId);

    /*
     * ~Description: 根据userId查name
     * ~Param: [userId]
     * ~return: [String]
     * ~Author: zhuyingce
     * ~Date: 2021/8/14
     */
    @Select("select name from teacher where userId = #{userId}")
    String queryNameByUserId(@Param("userId") Integer userId);

    /*
     * ~Description: 根据userId查number
     * ~Param: [userId]
     * ~return: [String]
     * ~Author: zhuyingce
     * ~Date: 2021/8/14
     */
    @Select("select number from teacher where userId = #{userId}")
    String queryNumByUserId(@Param("userId") Integer userId);

    /*
     * ~Description: 获取用户详细信息
     * ~Param: [userId]
     * ~return: [Register]
     * ~Author: zhuyingce
     * ~Date: 2021/8/17
     */
    @Select("select username,email,type,teacher.name,sex,number,nation," +
            "department.name as departmentName,position " +
            "from user,teacher,department " +
            "where user.id = #{userId} and user.id = teacher.userId and teacher.departmentId = department.id")
    User queryInfo(@Param("userId") Integer userId);

    /*
     * ~Description: 根据userId查id
     * ~Param: [userId]
     * ~return: [Integer]
     * ~Author: zhuyingce
     * ~Date: 2021/8/19
     */
    @Select("select id from teacher where userId = #{userId}")
    Integer queryIdByUserId(@Param("userId") Integer userId);
}
