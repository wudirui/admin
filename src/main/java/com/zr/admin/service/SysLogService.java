package com.zr.admin.service;

import java.util.List;
import java.util.Map;

public interface SysLogService {

    int addlogs(Map<String,Object> map);

    List<Map<String,Object>> getLogs();

    int del(int id);
}
