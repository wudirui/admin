package com.zr.admin.dao;

import com.zr.admin.bean.PermissionBean;

import java.util.List;

public interface PermissionDao {


    List<PermissionBean> getPermissionList(String userName);


}
