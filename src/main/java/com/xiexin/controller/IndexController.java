package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//热部署快捷键 ctrl+f9
@Controller
public class IndexController {
    //多个路径
    @RequestMapping({"/index","/"})
    public String index(){
        return "index";
    }

}
