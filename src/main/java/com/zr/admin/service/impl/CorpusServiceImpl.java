package com.zr.admin.service.impl;

import com.zr.admin.dao.ContentDao;
import com.zr.admin.dao.CorpusDao;
import com.zr.admin.service.CorpusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CorpusServiceImpl implements CorpusService {
    @Autowired
    ContentDao contentDao;
    @Autowired
    CorpusDao corpusDao;


    @Override
    public List<Map<String, Object>> getList(Map<String, Object> map) {
        List<Map<String, Object>> list = corpusDao.getList(map);
        return list;
    }

    @Override
    public void check(Map<String, Object> map) {
        corpusDao.check(map);
    }

    @Override
    public int delById(int id) {
        return corpusDao.delById(id);
    }

    @Override
    public List<Map<String, Object>> getOne(Map<String, Object> params) {
        return corpusDao.getOne(params);
    }
}
