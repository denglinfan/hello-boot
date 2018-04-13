package com.example.helloboot.springBoot.services.impl;

import com.example.helloboot.springBoot.db.dao.master.TestDao;
import com.example.helloboot.springBoot.services.ITestJdbc;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("testJdbc")
public class TestJdbc implements ITestJdbc {

    @Resource
    private TestDao testDao;

    @Override
    public String selectSoNo() {
        return testDao.selectTest();
    }
}
