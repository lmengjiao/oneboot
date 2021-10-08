package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping("/studentlist") // /page/studentlist
    public String studentlist(){
        return "studentlist";
    }
}
