package com.zr.admin.service;

import java.util.List;
import java.util.Map;

public interface SentenceService {
    List<Map<String, Object>> getList(Map<String, Object> params);

    int delById(int id);

    void updateSentence(Map<String, Object> map);

    void addSentence(String sentence, String createTime);
}
