package com.zr.admin.dao;

import com.zr.admin.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleDao {

    Role getRoleByUserId(String userName);
    // 添加角色
    int insert(Map<String,Object> map);
    // 给用户添加角色
    int insertUserRole(Map<String,Object> map);
    // 修改用户角色
    int updateUserRole(Map<String,Object> map);
    // 修改角色
    int update(Map<String,Object> map);

    // 角色授权  主要根据角色id   查询所拥有的菜单，  清空菜单， 再重新添加
    List<Map<String,Object>> getListByRoleId(int id);

    // 根据角色添加菜单
    int insertRolePermission(     Map<String,Object> map);
    // 根据 角色id 删除  当前角色的 菜单
    int delByRoleId(int id);

    Map<String,Object> getInfo(Map<String,Object> map);

    List<Map<String,Object>  > getList(Map<String,Object> map);


    int delById(int id);

    // 获取所有角色
    List< Map<String,Object>>  getAllRole();
    // 获取所有资源
    List< Map<String,Object>>  getAllPermission();

    // 根据角色名称 获取 角色 id
    Map<String,Object> getRoleIdByName(String name);

}
