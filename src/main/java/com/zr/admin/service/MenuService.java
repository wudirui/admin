package com.zr.admin.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface MenuService {
    List<Map<String, Object>> getList(Map<String, Object> params);

    int delById(Integer id);

    List<Map<Integer, String>> getMenus();

    void updateMenu(Map<String, Object> map);

    void addMenu(Map<String, Object> map);
}
