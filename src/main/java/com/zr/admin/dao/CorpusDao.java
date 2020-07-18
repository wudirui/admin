package com.zr.admin.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface CorpusDao {
    List<Map<String, Object>> getList(Map<String, Object> map);

    void check(Map<String, Object> map);

    int delById(int id);

    List<Map<String, Object>> getSentence(Map<String, Object> params);

    void addCorpus(Map<String, String> map);

    Integer getAlreadyRecordSentenceCount(JSONObject params);
}
