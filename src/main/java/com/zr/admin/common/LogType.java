package com.zr.admin.common;

/**
 * Created by Administrator on 2018/1/16.
 */
public enum LogType {
    SPACE(""),
    INSERT("添加"),
    DELETE("删除"),
    UPDATE("修改"),
    QUERY("查询"),
    IMPORT("导入"),
    EXPORT("导出"),
    LOGINOUT("退出"),
    LOGIN("登录");

    private String description;
    private LogType(String string) {
        description = string;
    }

    public String GetDescription()
    {
        return description;
    }
}
