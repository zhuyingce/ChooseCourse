package com.wust.system.dao;

import com.wust.system.entity.User;
import com.wust.system.pojo.Login;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-13
 */
@Repository
public interface UserDao {

    /*
     * ~Description: 注册添加一行
     * ~Param: [User]
     * ~return: [void]
     * ~Author: zhuyingce
     * ~Date: 2021/8/14
     */
    @Insert("insert into user(username,password,email,type) values(#{username},#{password},#{email},#{type})")
    void insert(User user);

    /*
    * ~Description: 查询id，判断登录
    * ~Param: [login]
    * ~return: Integer
    * ~Author: zhuyingce
    * ~Date: 2021/8/13
    */
    @Select("select id from user where username = #{username} and password = #{password} and type = #{type}")
    Integer queryLogin(Login login);

    /*
    * ~Description: 判断用户名是否重复
    * ~Param: [username]
    * ~return: Integer
    * ~Author: zhuyingce
    * ~Date: 2021/8/14
    */
    @Select("select id from user where username = #{username}")
    Integer queryIdByUsername(@Param("username") String username);

    /*
     * ~Description: 根据userId查类型
     * ~Param: [userId]
     * ~return: [String]
     * ~Author: zhuyingce
     * ~Date: 2021/8/17
     */
    @Select("select type from user where id = #{userId}")
    String queryTypeByUserId(@Param("userId") Integer userId);

    /*
    * ~Description: 根据Id查用户名
    * ~Param: [id]
    * ~return: [String]
    * ~Author: zhuyingce
    * ~Date: '2021/9/23'
    */
    @Select("select username from user where id = #{id}")
    String queryUsernameById(@Param("id") Integer id);
}
