package com.zr.admin.service.impl;

import com.zr.admin.dao.SystemLogDao;
import com.zr.admin.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private SystemLogDao sysLogDao;



    @Override
    public int addlogs(Map<String, Object> map) {
        return sysLogDao.addlogs(map);
    }

    @Override
    public List<Map<String, Object>> getLogs() {
        return sysLogDao.getLogs();
    }

    @Override
    public int del(int id) {
        return sysLogDao.del(id);
    }
}
