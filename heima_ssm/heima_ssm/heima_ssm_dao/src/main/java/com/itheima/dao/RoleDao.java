package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    //根据用户id查询到所有角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionByRoleId"))
    })
    List<Role> findRoleByUserId(String userId);


    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findRoleByIdAndAllPermission(String roleId);

    @Insert("insert into role_permission (roleId,permissionId) values(#{permissionId},#{roleId})")
    void addPermissionToRole( @Param("permissionId") String permissionId,@Param("roleId") String roleId);
}
