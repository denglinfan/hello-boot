package com.example.helloboot.springBoot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello,this is a springBoot demo!!!";
    }

    @RequestMapping("/testDevTools")
    public String testDevTools(){
        return "hello,testDevTools is successful!!!";
    }
}
