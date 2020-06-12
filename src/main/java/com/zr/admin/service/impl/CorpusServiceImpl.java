package com.zr.admin.service.impl;

import com.zr.admin.common.Corpus;
import com.zr.admin.dao.ContentDao;
import com.zr.admin.dao.CorpusDao;
import com.zr.admin.service.CorpusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CorpusServiceImpl implements CorpusService {
    @Autowired
    ContentDao contentDao;
    @Autowired
    CorpusDao corpusDao;

    @Override
    public List<Corpus> findList() {
        return null;
    }

    @Override
    public int check() {
        return 0;
    }
}
