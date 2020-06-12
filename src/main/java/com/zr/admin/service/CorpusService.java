package com.zr.admin.service;

import com.zr.admin.common.Corpus;
import com.zr.admin.dao.ContentDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CorpusService {

    //获取语料列表
    List<Corpus> findList();

    //审核
    int check();


}
