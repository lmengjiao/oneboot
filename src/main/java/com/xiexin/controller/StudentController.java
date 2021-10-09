package com.xiexin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiexin.bean.Student;
import com.xiexin.bean.StudentExample;
import com.xiexin.respcode.Result;
import com.xiexin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //分页查询
    @RequestMapping("/selectPageAll")
    public Result selectPageAll(Student student,@RequestParam(value="page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(value="limit",defaultValue = "5",required = true) Integer pageSize) {
        System.out.println("student = " + student);
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        //使用pageHer分页
        PageHelper.startPage(page, pageSize);
        if(null!=student.getStudentSex()&&!"".equals(student.getStudentSex())){
            criteria.andStudentSexEqualTo(student.getStudentSex());
        }
        if(null!=student.getStudentName()&&!"".equals(student.getStudentName())){
            criteria.andStudentNameEqualTo(student.getStudentName());
        }
        List<Student> students = studentService.selectByExample(example);
        PageInfo pageInfo = new PageInfo(students);
        Result result = new Result(pageInfo);
        return result;
    }

    //搜索框 学号 姓名 性别 身份证号
//    public Result selectBysousuo(){
//        studentService.selectByExample(studentId,)
//    }
}
