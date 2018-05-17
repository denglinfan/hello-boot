package com.example.helloboot.springBoot.controller;

import com.example.helloboot.springBoot.pojo.AuthorSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

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

    /**
     * 注入该对象，为了能够在日志中打印服务对相关内容，并且我们需要在applcation.properties文件中
     * 通过配置spring.application.name属性来为服务命名
     * 通过配置eurake.client.serviceUrl.defaultZone属性来指定服务注册中心的地址
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        LOGGER.info("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
        return "hello,this is a springBoot demo!!!author:" + author + ", name:" + name;
    }

    @RequestMapping("/testDevTools")
    public String testDevTools(){
        return "hello,this is a springBoot demo!!!author:" + authorSettings.getAuthor() + ", name:" + authorSettings.getName();
    }
}
