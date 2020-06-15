package com.zr.admin.dao;

import java.util.List;
import java.util.Map;

public interface ContentDao {
    List<Map<String, Object>> getOne(Map<String, Object> params);
}
