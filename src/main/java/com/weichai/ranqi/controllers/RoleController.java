package com.weichai.ranqi.controller;

import com.weichai.ranqi.entity.Role;
import com.weichai.ranqi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // 获取角色列表
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    // 新增角色
    @PostMapping
    public String addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return "角色新增成功！";
    }

    // 编辑角色
    @PutMapping("/{id}")
    public String updateRole(@PathVariable Long id, @RequestBody Role role) {
        roleService.updateRole(id, role);
        return "角色编辑成功！";
    }

    // 删除角色
    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return "角色删除成功！";
    }
}
