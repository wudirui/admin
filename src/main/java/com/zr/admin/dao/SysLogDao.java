package com.zr.admin.dao;

import java.util.List;
import java.util.Map;

public interface SysLogDao {

    int addlogs(Map<String,Object> map);

    List<Map<String,Object>> getLogs();

    int del(int id);

}
