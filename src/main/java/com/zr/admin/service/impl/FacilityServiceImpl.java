package com.zr.admin.service.impl;

import com.zr.admin.dao.FacilityDao;
import com.zr.admin.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityDao facilityDao;

    @Override
    public Map<String, Object> getInfo(Map<String, Object> map) {
        return facilityDao.getInfo(map);
    }

    @Override
    public int insert(Map<String, Object> map) {
        return facilityDao.insert(map);
    }

    @Override
    public int update(Map<String, Object> map) {
        return facilityDao.update(map);
    }

    @Override
    public List<Map<String,Object>  > getList(Map<String, Object> map) {
        return facilityDao.getList(map);
    }

    @Override
    public int delById(int id) {
        return facilityDao.delById(id) ;
    }

    @Override
    public int untying(int id) {
        return facilityDao.untying(id);
    }
}
