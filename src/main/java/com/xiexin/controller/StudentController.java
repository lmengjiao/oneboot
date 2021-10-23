package com.xiexin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiexin.bean.Student;
import com.xiexin.bean.StudentExample;
import com.xiexin.respcode.Result;
import com.xiexin.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Api(tags = "学生的信息",description = "学生的基本信息 如curd")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //使用@ExceptionHandler处理异常 该注解的作用域仅仅作用该类 该类需要该注解 在controller中 统一异常
    @ExceptionHandler(ArithmeticException.class)
    public Result studentException(ArithmeticException e){
        return new Result("5001","系统繁忙，出错了");
    }

    //全查 注意 不用map 公司中都会用一个类 或者好几个类组成 叫做统一响应类 每个公司都不一样
    @ApiOperation(value = "查询全部的学生")
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)  // /student/selectAll
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
        int i=9/0; //算数异常
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

    //编辑框中的修改
    @RequestMapping("/updateOne") // /student/updateOne
    public Result updateOne(@RequestBody Student student){
        System.out.println("student = " + student);
        int i = studentService.updateByPrimaryKeySelective(student);
        if(i==1){
            return new Result();
        }else{
            return new Result(4001,"修改失败");
        }
    }
}
