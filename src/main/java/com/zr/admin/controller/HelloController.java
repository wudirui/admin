package com.zr.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "hello/")
public class HelloController {

    @RequestMapping("say")
    public String say(){
        return "index";
    }
}
