package com.zr.admin.dao;

import com.zr.admin.bean.Permission;

import java.util.List;

public interface PermissionDao {


    List<Permission> getPermissionList(String userName);


}
