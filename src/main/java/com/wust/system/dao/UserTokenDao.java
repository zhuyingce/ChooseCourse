package com.wust.system.dao;

import com.wust.system.entity.UserToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-14
 */
@Repository
public interface UserTokenDao {

    /*
     * ~Description: 第一次登录添加token
     * ~Param: [UserToken]
     * ~return: [void]
     * ~Author: zhuyingce
     * ~Date: 2021/8/14
     */
    @Insert("insert into userToken(token,userId) values(#{token},#{userId})")
    void insertToken(UserToken userToken);

    /*
    * ~Description: 每次登录修改token
    * ~Param: [UserToken]
    * ~return: void
    * ~Author: zhuyingce
    * ~Date: 2021/8/14
    */
    @Update("update userToken set token = #{token} where userId = #{userId}")
    void updateToken(UserToken userToken);

    /*
    * ~Description: 根据userId查
    * ~Param: [userId]
    * ~return: Integer
    * ~Author: zhuyingce
    * ~Date: 2021/8/14
    */
    @Select("select id from userToken where userId = #{userId}")
    Integer queryIdByUserId(@Param("userId") Integer userId);
    
    /*
    * ~Description: 根据userId查询token
    * ~Param: [userId]
    * ~return: String
    * ~Author: zhuyingce
    * ~Date: 2021/8/14
    */
    @Select("select token from userToken where userId = #{userId}")
    String queryTokenByUserId(@Param("userId") Integer userId);
}
