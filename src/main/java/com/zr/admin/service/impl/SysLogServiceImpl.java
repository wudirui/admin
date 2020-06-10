package com.zr.admin.service.impl;

import com.zr.admin.dao.SysLogDao;
import com.zr.admin.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;



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
