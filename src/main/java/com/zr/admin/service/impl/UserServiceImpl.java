package com.zr.admin.service.impl;

import com.zr.admin.bean.User;
import com.zr.admin.common.Common;
import com.zr.admin.dao.UserDao;
import com.zr.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String userName) {
        return userDao.getUser(userName);
    }

    @Override
    public List<Map<String, Object>> getList(Map<String, Object> map) {
        return userDao.getList(map);
    }

    @Override
    public int addUser(Map<String, Object> map) {
        // 判断账号 是否已经注册
        User re = userDao.getUser(map.get("userName").toString());
        if( null != re ){
            return  -1;
        }
        return userDao.addUser(map);
    }



    @Override
    public int delUserByid(int id) {
        Map<String ,Object> map =     userDao.getRoleIdByUserId(id);
        if(null != map){
            if(Common.ADMINID.equals(  String.valueOf(id))){
                // 说明是超级管理员账号 不能删除
                return  -1;
            }
        }
        return userDao.delUserByid(id);
    }

    @Override
    public int updateUser(Map<String, Object> map) {
        return userDao.updateUser(map);
    }

    @Override
    public List<Map<String, Object>> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public Map<String, Object> getRoleIdByUserId(int id) {
        return userDao.getRoleIdByUserId(id);
    }


}
