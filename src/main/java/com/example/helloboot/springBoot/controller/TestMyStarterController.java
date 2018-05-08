package com.example.helloboot.springBoot.controller;

import com.wisely.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMyStarterController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/myStarter")
    public String index(){
        return "charles";//helloService.sayHello();
    }
}
