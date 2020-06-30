package com.zr.admin.controller;

import com.zr.admin.common.LogType;
import com.zr.admin.common.LoggerManager;
import com.zr.admin.common.PageUtils;
import com.zr.admin.service.SystemLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/syslog")
public class SystemLogController {

    @Autowired
    private SystemLogService sysLogService;

    private static final Logger logger = Logger.getLogger(SystemLogController.class);
    @RequestMapping("/getlist")
    @LoggerManager(type = LogType.QUERY, module = "日志模块", logDesc = "分页获取日志列表")
    public Object list(@RequestParam(defaultValue = "1", required = true) int page,
                       @RequestParam(defaultValue = "10", required = true) Integer limit,
                       HttpServletResponse response) {
        //  response.addHeader("Access-Control-Allow-Origin", "http://192.168.1.101:8848");
        PageHelper.startPage(page, limit);
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> list = sysLogService.getLogs();
        PageInfo<Map<String, Object>> info = new PageInfo<>(list);
        //  model.addAttribute("pageInfo",info);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setCode("0");
        pageUtils.setCount(String.valueOf(info.getTotal()));
        pageUtils.setData(info.getList());
        return pageUtils;
    }


    @RequestMapping("/del")
    @LoggerManager(type = LogType.DELETE, module = "日志模块", logDesc = "删除日志")
    public Object delLog(@RequestParam Map<String, Object> map) {
        int id = -1;
        int i = 0;
        if (map.containsKey("id")) {
            if (!StringUtils.isBlank(map.get("id").toString())) {
                id = Integer.valueOf(map.get("id").toString());
                i = sysLogService.del(id);
            }
        }

        return i;
    }


    @RequestMapping("/batchdel")
    @LoggerManager(type = LogType.DELETE, module = "日志模块", logDesc = "批量删除日志")
    public Object batchDel(@RequestParam Map<String, Object> map) {
        if (map.containsKey("ids")) {
            // String[] ids = map.get("ids").toString().split(",")
            String[] ids = map.get("ids").toString().split(",");
            for (String id : ids) {
                sysLogService.del(Integer.valueOf(id));
            }
        }
        return "ok";


    }


}
