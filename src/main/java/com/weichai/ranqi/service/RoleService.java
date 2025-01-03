package com.weichai.ranqi.service;

import com.weichai.ranqi.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void addRole(Role role);
    void updateRole(Long id, Role role);
    void deleteRole(Long id);
}
