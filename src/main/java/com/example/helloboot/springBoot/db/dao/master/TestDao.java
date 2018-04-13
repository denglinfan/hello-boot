package com.example.helloboot.springBoot.db.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Mapper@Repository
public interface TestDao {

    public String selectTest();
}
