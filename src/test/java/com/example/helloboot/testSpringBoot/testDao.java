package com.example.helloboot.testSpringBoot;

import com.example.helloboot.springBoot.services.impl.TestJdbc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testDao {


    @Resource
    public TestJdbc testJdbc;

    @Test
    public void test(){
        String soNo = testJdbc.selectSoNo();
        System.out.println(soNo);
    }

}
