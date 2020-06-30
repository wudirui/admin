package com.zr.admin.bean;

import java.util.Set;

public class RoleBean {

    private  int id;
    private  String  name;
    private  String desc;
    private Set<PermissionBean> permissionList;

    public Set<PermissionBean> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<PermissionBean> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
