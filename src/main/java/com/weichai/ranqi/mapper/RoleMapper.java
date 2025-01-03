package com.weichai.ranqi.mapper;

import com.weichai.ranqi.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM roles")
    List<Role> selectAllRoles();

    @Insert("INSERT INTO roles (permissions, description) VALUES (#{permissions}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertRole(Role role);

    @Update("UPDATE roles SET permissions = #{permissions}, description = #{description} WHERE id = #{id}")
    void updateRole(Role role);

    @Delete("DELETE FROM roles WHERE id = #{id}")
    void deleteRole(Long id);
}
