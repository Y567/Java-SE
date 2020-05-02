package com.gyy.dao;

import com.gyy.domain.User;
import org.apache.ibatis.annotations.*;


public interface UserMapper {

    @Select("select * from user where id = #{id} and name = #{name}")
    User findUserById(@Param("id") int id, @Param("name") String name);

    @Update("update user set name = #{name},pwd = #{password} where id = #{id}")
    void updateUser(User user);

    @Insert("insert into user values(#{id},#{name},#{password})")
    void insertUser(User user);

    @Delete("delete from user where id = #{id}")
    void deleteUserById(@Param("id") int id);
}
