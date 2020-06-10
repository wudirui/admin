package com.zr.admin.service;

import java.util.List;
import java.util.Map;

public interface FacilityService {

    Map<String,Object> getInfo(Map<String,Object> map);
    int insert(Map<String,Object> map);
    // 修改
    int update(Map<String,Object> map);


    List<Map<String,Object>> getList(Map<String,Object> map);


    int delById(int id);
    // 解除 跟用户的 设备 绑定
    int untying(int id);


}
