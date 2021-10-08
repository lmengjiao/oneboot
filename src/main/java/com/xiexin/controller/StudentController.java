package com.xiexin.controller;

import com.xiexin.bean.Student;
import com.xiexin.respcode.Result;
import com.xiexin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    //全查 注意 不用map 公司中都会用一个类 或者好几个类组成 叫做统一响应类 每个公司都不一样
    @RequestMapping("/selectAll")  // /student/selectAll
    public Result selectAll(){
        List<Student> students = studentService.selectByExample(null);
        Result<Student> result = new Result(students);
        return result;
        //return new Result(studentService.selectByExample(null));
    }

    //分页查询（带参数）
    @RequestMapping("/selectPageAll")
    public Result selectPageAll(){
        return null;
    }
}
