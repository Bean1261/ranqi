package com.weichai.ranqi.service.impl;

import com.weichai.ranqi.entity.Role;
import com.weichai.ranqi.mapper.RoleMapper;
import com.weichai.ranqi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectAllRoles();
    }

    @Override
    public void addRole(Role role) {
        roleMapper.insertRole(role);
    }

    @Override
    public void updateRole(Long id, Role role) {
        role.setId(id);
        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleMapper.deleteRole(id);
    }
}
