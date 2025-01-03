package com.weichai.ranqi.mapper;

import com.weichai.ranqi.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // 插入用户
    @Insert("INSERT INTO user (name, password, phone, permissions) VALUES (#{name}, #{password}, #{phone}, #{permissions})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    // 根据 ID 查询用户
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUserById(Long id);

    // 查询所有用户
    @Select("SELECT * FROM user")
    List<User> selectAllUsers();

    // 更新用户信息
    @Update("UPDATE user SET name = #{name}, password = #{password}, phone = #{phone}, permissions = #{permissions} WHERE id = #{id}")
    int updateUser(User user);

    // 根据 ID 删除用户
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUserById(Long id);
}
