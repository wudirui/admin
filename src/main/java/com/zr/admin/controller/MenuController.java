package com.zr.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zr.admin.common.PageUtils;
import com.zr.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

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
        List<Map<String, Object>> list = menuService.getList(params);
        PageInfo<Map<String, Object>> info = new PageInfo<>(list);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setCode("0");
        pageUtils.setCount(String.valueOf(info.getTotal()));
        pageUtils.setData(info.getList());
        return pageUtils;
    }

    @RequestMapping("del")
    public Object del(@RequestParam Map<String, Object> map) {
        if (!map.containsKey("id")) {
            return "id为空";
        }

        int id = Integer.valueOf(map.get("id").toString());
        int i = menuService.delById(id);
        return i;
    }

    @RequestMapping("delAll")
    public Object delAll(@RequestParam Map<String, Object> map) {
        String[] ids = map.get("ids").toString().split(",");
        int i = -1;
        for (String id : ids) {
            if (id != null && !id.equals("")) {
                i = menuService.delById(Integer.valueOf(id));
            }
        }
        return i;
    }

    @RequestMapping(value = "getMenus", method = RequestMethod.POST)
    public Object getMenus() {
        List<Map<Integer, String>> menus = menuService.getMenus();
        return menus;
    }
}
