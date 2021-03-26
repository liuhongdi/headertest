package com.headertest.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

    //显示home页面
    @GetMapping("/home")
    public String login() {
        return "home/home";
    }

    //接收header参数
    @GetMapping("/send")
    @ResponseBody
    public String send(HttpServletRequest httpServletRequest) {
        String h1 = httpServletRequest.getHeader("h1");
        String h2 = httpServletRequest.getHeader("h2");
        String h3 = httpServletRequest.getHeader("h3");
        return "res:"+h1+"_"+h2+"_"+h3;
    }
}
