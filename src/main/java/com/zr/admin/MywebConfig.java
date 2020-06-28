package com.zr.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
public class MywebConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/zxc/foo").setViewName("foo");
//        registry.addViewController("/wch/receive");
//    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","/wch/getSentence","/wch/sendAudio","/login.html","/page/404.html"
                        ,"/css/**","/images/**","/js/**","/layui/**","/page/*/*.js")
                ;

    }

    /**
     * 添加静态资源--过滤swagger-api (开源的在线API文档)
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("login.html")
//                .addResourceLocations("classpath:/static/login.html");
//    }

    /**
     * 跨域支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

}