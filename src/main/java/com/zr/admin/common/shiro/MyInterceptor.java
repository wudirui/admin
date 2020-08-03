package com.zr.admin.common.shiro;

import com.zr.admin.bean.UserBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyInterceptor implements HandlerInterceptor {
    //在请求处理之前进行调用（Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("preHandle被调用");
     //   Map map =(Map)httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String paths = request.getServletPath();

        String urlString = request.getRequestURI();
        System.out.println(paths);

        if(urlString.endsWith("login.html")|| urlString.endsWith("login")){
            return true;
        }
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        UserBean user = (UserBean) session.getAttribute("loginUser"); // 登录用户
        List<String> list = (ArrayList) session.getAttribute("permission"); // 用户所有的资源
        // 截取 请求的资源最后 URL
        String endUrl =  urlString.substring(urlString.lastIndexOf("/")+1,urlString.length()) ;
        if(null != user) {
            // 标识 用户已登录
            // 验证其资源 是否有权限访问
            // 只拦截html  主页不拦截
//            if(urlString.endsWith("index.html")|| urlString.endsWith("main.html"))  return true;
//            if(urlString.endsWith("html")){
//                for (String  s : list){
//                    if(s.endsWith(endUrl)){  // 说明 用户有此权限，，可以访问
//                        return true;
//                    }
//                }
//                // 没有访问权限，去404 吧
//                response.sendRedirect("/page/404.html");
//                return  false;
//            }else {
//                return true;
//            }

           return true;    //如果false，停止流程，api被拦截
        }else {
            response.sendRedirect("/login.html");
            return  false;

        }
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion被调用");
    }
}