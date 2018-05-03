package com.example.helloboot.springBoot.controller;

import com.example.helloboot.springBoot.pojo.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * 可以通过@Value注解直接将properties中的配置读取出来
     */
    @Value("${book.author}")
    private String author;
    @Value("${book.name}")
    private String name;


    /**
     * 2.也可以通过类型安全的配置，使用@ConfigurationProperties将一个properties属性和一个
     *    实体类关联起来，并通过@Autowired注入，并使用
     */
    @Autowired
    private AuthorSettings authorSettings;

    @RequestMapping("/hello")
    public String hello(){
        return "hello,this is a springBoot demo!!!author:" + author + ", name:" + name;
    }

    @RequestMapping("/testDevTools")
    public String testDevTools(){
        return "hello,this is a springBoot demo!!!author:" + authorSettings.getAuthor() + ", name:" + authorSettings.getName();
    }
}
