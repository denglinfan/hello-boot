package com.example.helloboot.springBoot.db.dao.salve;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper@Repository
public interface TestDao1 {

    public String selectTest();
}
