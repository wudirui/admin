package com.zr.admin.service.impl;

import com.zr.admin.dao.SentenceDao;
import com.zr.admin.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SentenceServiceImpl implements SentenceService {
    @Autowired
    SentenceDao sentenceDao;

    @Override
    public List<Map<String, Object>> getList(Map<String, Object> params) {
        return sentenceDao.getList(params);
    }

    @Override
    public int delById(int id) {
        return sentenceDao.delById(id);
    }

    @Override
    public void updateSentence(Map<String, Object> map) {
        sentenceDao.updateSentence(map);
    }

    @Override
    public void addSentence(String sentence, String createTime) {
        sentenceDao.addSentence(sentence,createTime);
    }
}
