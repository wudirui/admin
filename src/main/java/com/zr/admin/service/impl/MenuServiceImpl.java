package com.zr.admin.service.impl;

import com.zr.admin.dao.MenuDao;
import com.zr.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuDao;

    @Override
    public List<Map<String, Object>> getList(Map<String, Object> params) {
        return menuDao.getList(params);
    }

    @Override
    public int delById(Integer id) {
        return menuDao.delById(id);
    }

    @Override
    public List<Map<Integer, String>> getMenus() {
        return menuDao.getMenus();
    }
}
