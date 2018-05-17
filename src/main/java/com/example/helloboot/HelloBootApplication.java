package com.example.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *  使用@EnableDiscoveryClient 注解用来激活Eureka中的DiscoveryClient实现
 *  才能实现在HelloController中/hello对服务信息对输出
 */
@EnableDiscoveryClient
@SpringBootApplication
public class HelloBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloBootApplication.class, args);
	}
}
