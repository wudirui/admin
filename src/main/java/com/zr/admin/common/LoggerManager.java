package com.zr.admin.common;



import java.lang.annotation.*;

/**
 * 自定义注解日志类
 * Created by Administrator on 2018/1/11.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManager {
     LogType type() default LogType.SPACE;
     String module()  default "";  //模块
     String logDesc() default ""; //描述信息
}
