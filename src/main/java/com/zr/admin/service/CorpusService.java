package com.zr.admin.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface CorpusService {

    //列表
    List<Map<String, Object>> getList(Map<String, Object> map);
    //审核
    void check(Map<String, Object> map);

    int delById(int id);



    List<Map<String, Object>> getSentence(Map<String, Object> params);

    void addCorpus(Map<String, String> map);

    Integer getAlreadyRecordSentenceCount(JSONObject params);
}
