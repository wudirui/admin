package com.zr.admin.bean;

import java.io.Serializable;
import java.util.Date;

public class SystemLogBean implements Serializable {

    private int id;
    private String logTypeName;
    private String sysLogModule;
    private String sysLogMethods;
    private String sysLogResult;
    private String sysLogIp;
    private Date logCreateDate;
    private Long logHandleTimes;
    private String sysLogDesc;
    private String logErrorMsg;
    private String logCreateUser;


}
