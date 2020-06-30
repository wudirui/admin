package com.zr.admin.service;

import com.zr.admin.bean.UserBean;

import java.util.List;
import java.util.Map;

public interface UserService {
   UserBean getUser(String userName);
    List<Map<String, Object>> getList(Map<String, Object> map);
    int  addUser(Map<String,Object> map);

    int delUserByid(int id);

    int updateUser(Map<String,Object> map);

    List< Map<String,Object>   > getAllUser();

  Map<String,Object> getRoleIdByUserId(int id );

}
