package com.zr.admin.controller;


import com.zr.admin.bean.Role;
import com.zr.admin.common.Common;
import com.zr.admin.common.LogType;
import com.zr.admin.common.LoggerManager;
import com.zr.admin.common.PageUtils;
import com.zr.admin.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;


    @RequestMapping("/add")
    @LoggerManager(type = LogType.INSERT, module = "角色模块", logDesc = "添加角色")
    public Object add(@RequestParam Map<String, Object> map) {

        if (map.containsKey("id")) {
            System.out.println("  有id");

            String id = map.get("id").toString();

            if (!StringUtils.isBlank(id)) { //  有id 就修改
                roleService.update(map);
                return "ok";
            }
        }

        String rName = map.get("name").toString();
        Map<String, Object> re = roleService.getRoleIdByName(rName);
        if (null != re) {
            // 说明角色 已经存在
            return "角色 已经存在";
        }

        // 没有id 说明是添加
        Object i = roleService.insert(map);
        return i;
    }

    @RequestMapping("/getlist")
    @LoggerManager(type = LogType.QUERY, module = "角色模块", logDesc = "获取角色分页列表")
    public Object getList(@RequestParam(defaultValue = "1", required = true) int page,
                          @RequestParam(defaultValue = "10", required = true) Integer limit,
                          HttpServletResponse response) {
        PageHelper.startPage(page, limit);
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = roleService.getList(map);
        PageInfo<Map<String, Object>> info = new PageInfo<>(list);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setCode("0");
        pageUtils.setCount(String.valueOf(info.getTotal()));
        pageUtils.setData(info.getList());
        System.out.println("用户角色分页");
        return pageUtils;
    }

    @RequestMapping("/getallrole")
    public Object getAllRole() {

        return roleService.getAllRole();
    }

    @RequestMapping("/getallpermission")
    public Object getAllPermission() {
        Object i = roleService.getAllPermission();
        return i;
    }

    @RequestMapping("/getrolemenu")
    public Object getrolemenu(HttpServletRequest request) {
        logger.info("--------------获取菜单开始------------------");
        int id = 0;
        Role r = (Role) request.getSession().getAttribute("role");
        if (null == r) {
            return "error";
        }
        id = r.getId();
        if (0 != id) {
            List<Map<String, Object>> list = roleService.getListByRoleId(id);
            Map<String, Object> returnMap = new HashMap<>();
            List<Map<String, Object>> userMu = new ArrayList<>();
            List<Map<String, Object>> facilityMu = new ArrayList<>();
            List<Map<String, Object>> sysMu = new ArrayList<>();
            List<Map<String, Object>> docMu = new ArrayList<>();
            for (Map<String, Object> m : list) {
                if (m.containsKey("pid")) {
                    if (Common.USERMANAGER.equals(m.get("pid").toString())) {
                        userMu.add(m);
                    }
                }
                if (m.containsKey("pid")) {
                    if (Common.FACILITYMANAGER.equals(m.get("pid").toString())) {
                        facilityMu.add(m);
                    }
                }
                if (m.containsKey("pid")) {
                    if (Common.SYSMANAGER.equals(m.get("pid").toString())) {
                        sysMu.add(m);
                    }
                }
                if (m.containsKey("pid")) {
                    if (Common.DOCUMENT.equals(m.get("pid").toString())) {
                        docMu.add(m);
                    }
                }
            }
            returnMap.put("memberCenter", userMu);
            returnMap.put("contentManagement", facilityMu);
            returnMap.put("systemeSttings", sysMu);
            returnMap.put("seraphApi", docMu);
            logger.info("--------------获取菜单结束------------------");
            return returnMap;
        }
        return "用户角色获取失败";

    }

    @RequestMapping("/getinfo")
    public Object getInfo(@RequestParam Map<String, Object> map) {

        return roleService.getInfo(map);
    }

    @RequestMapping("/update")
    @LoggerManager(type = LogType.UPDATE, module = "角色模块", logDesc = "修改角色")
    public Object update(@RequestParam Map<String, Object> map) {

        return roleService.update(map);
    }

    @RequestMapping("/del")
    @LoggerManager(type = LogType.DELETE, module = "角色模块", logDesc = "删除角色")
    public Object del(@RequestParam Map<String, Object> map) {

        if (map.containsKey("id")) {

            return roleService.delById(Integer.valueOf(map.get("id").toString()));
        }
        return "error";
    }


    @RequestMapping("/getpbyrid")
    public Object getPermissionByRoleId(@RequestParam Map<String, Object> map) {

        if (map.containsKey("id") && !StringUtils.isBlank(map.get("id").toString())) {
            int id = Integer.valueOf(map.get("id").toString());
            List<Map<String, Object>> list = roleService.getListByRoleId(id);
            return list;
        }
        return "error";
    }

    @RequestMapping("/roleauthor") // 角色授权
    @LoggerManager(type = LogType.UPDATE, module = "角色模块", logDesc = "角色授权")
    public Object roleAuthor(@RequestParam Map<String, Object> map) {

        int re = -1;
        try {
            if (map.containsKey("id") && !StringUtils.isBlank(map.get("id").toString())) {
                if (map.containsKey("permissionIds") && !StringUtils.isBlank(map.get("permissionIds").toString())) {
                    //
                    int roleId = Integer.valueOf(map.get("id").toString());

                    // 先清空角色 资源
                    int delp = roleService.delByRoleId(roleId);
                    // 在重新添加
                    String[] ps = map.get("permissionIds").toString().split(",");
                    for (String pid : ps) {
                        Map<String, Object> params = new HashMap<>();
                        params.put("rid", roleId);
                        params.put("pid", pid);
                        re = roleService.insertRolePermission(params);
                        return re;
                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
        return re;

    }


}
