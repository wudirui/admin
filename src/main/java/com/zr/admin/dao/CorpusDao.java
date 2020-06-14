package com.zr.admin.dao;

import java.util.List;
import java.util.Map;

public interface CorpusDao {
    List<Map<String, Object>> getList(Map<String, Object> map);

    void check(Map<String, Object> map);

    int delById(int id);
}
