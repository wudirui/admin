package com.zr.admin.service.impl;

import com.zr.admin.bean.RoleBean;
import com.zr.admin.dao.RoleDao;
import com.zr.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public RoleBean getRoleByUserId(String userName) {
        return roleDao.getRoleByUserId(userName);
    }

    @Override
    public int insert(Map<String, Object> map) {
        return roleDao.insert(map);
    }

    @Override
    public int update(Map<String, Object> map) {
        return roleDao.update(map);
    }

    @Override
    public List<Map<String, Object>> getListByRoleId(int id) {
        return roleDao.getListByRoleId(id);
    }

    @Override
    public int insertRolePermission(Map<String, Object> map) {

        return roleDao.insertRolePermission(map);

    }

    @Override
    public int delByRoleId(int id) {
        return roleDao.delByRoleId(id);
    }

    @Override
    public Map<String, Object> getInfo(Map<String, Object> map) {
        return roleDao.getInfo(map);
    }

    @Override
    public List<Map<String, Object>> getList(Map<String, Object> map) {
        return roleDao.getList(map);
    }

    @Override
    public int delById(int id) {
        return roleDao.delById(id);
    }

    @Override
    public int insertUserRole(Map<String, Object> map) {
        return roleDao.insertUserRole(map);
    }

    @Override
    public int updateUserRole(Map<String, Object> map) {
        return roleDao.updateUserRole(map);
    }

    @Override
    public List<Map<String, Object>> getAllRole() {
        return roleDao.getAllRole();
    }

    @Override
    public List<Map<String, Object>> getAllPermission() {
        return roleDao.getAllPermission();
    }

    @Override
    public Map<String,Object> getRoleIdByName(String name) {
        return roleDao.getRoleIdByName(name);
    }
}
