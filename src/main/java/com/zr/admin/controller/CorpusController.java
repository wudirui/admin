package com.zr.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zr.admin.common.PageUtils;
import com.zr.admin.service.CorpusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 语料库管理
 */
@RestController
@RequestMapping(value = "/corpus")
public class CorpusController {

    @Autowired
    CorpusService corpusService;

    @RequestMapping("getlist")
    @ResponseBody
    public Object list(@RequestParam(defaultValue = "0", required = true) int page,
                       @RequestParam(defaultValue = "10", required = true) int limit,
                       @RequestParam Map<String, Object> params) {
        PageHelper.startPage(page, limit);
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getValue() == null || entry.getValue() == "") {
                params.put((String) entry.getKey(), null);
            }
        }
        List<Map<String, Object>> list = corpusService.getList(params);
        PageInfo<Map<String, Object>> info = new PageInfo<>(list);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setCode("0");
        pageUtils.setCount(String.valueOf(info.getTotal()));
        pageUtils.setData(info.getList());
        return pageUtils;
    }

    @RequestMapping("check")
    public Object check(@RequestParam Map<String, Object> map) {
        if (!map.containsKey("id")) {
            return "id为空";
        }
        corpusService.check(map);
        return "success";
    }

    @RequestMapping("del")
    public Object del(@RequestParam Map<String, Object> map) {
        if (!map.containsKey("id")) {
            return "id为空";
        }

        int id = Integer.valueOf(map.get("id").toString());
        int i = corpusService.delById(id);
        return i;
    }

    @RequestMapping("delAll")
    public Object delAll(@RequestParam Map<String, Object> map) {
        String[] ids = map.get("ids").toString().split(",");
        int i = -1;
        for (String id : ids) {
            if (id != null && !id.equals("")) {
                i = corpusService.delById(Integer.valueOf(id));
            }
        }
        return i;
    }
}
