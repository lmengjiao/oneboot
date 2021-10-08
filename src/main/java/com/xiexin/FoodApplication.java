package com.xiexin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//springboot项目是为了简化ssm项目而存在
//ssm的项目配置比较繁琐 需要配置Tomcat 需要很多个xml去配置第三方依赖
//springboot简化成该内置就内置 多个xml配置改为一个properties/xml文件
//springboot本质还是ssm框架 只不过写起来更简单了
@SpringBootApplication //springboot的应用 标记本项目是springboot项目 必须有这个注解
@MapperScan("com.xiexin.dao") //包扫描
public class FoodApplication {
    //main方法 项目一启动就会执行
    public static void main(String[] args) {
        //静态的调用 SpringApplication应用 参数为本项目的启动类
        System.out.println("我启动了");
        SpringApplication.run(FoodApplication.class, args);
    }

}
