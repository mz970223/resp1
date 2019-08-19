package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(String roleId);

    List<Permission> findRoleByIdAndAllPermission(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
