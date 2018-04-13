package com.example.helloboot.springBoot.db.dataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com/example/helloboot/springBoot/db/dao/salve", sqlSessionTemplateRef = "sqlSessionTemplateSec")
public class SecondaryDataSourceConfig{

    @Bean(name = "dataSourceSec")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource(DataSourceProperties properties){
        return DataSourceBuilder.create(properties.getClassLoader())
                .type(HikariDataSource.class)
                .driverClassName(properties.determineDriverClassName())
                .username(properties.determineUsername())
                .password(properties.determinePassword())
                .url(properties.determineUrl())
                .build();
    }

    @Bean(name = "sqlSessionFactorySec")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("dataSourceSec") DataSource dataSource) throws  Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        return bean.getObject();
    }

    @Bean(name = "transactionManagerSec")
    public DataSourceTransactionManager secondaryTransactionManager(@Qualifier("dataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplateSec")
    public SqlSessionTemplate secondarySqlSessionTemplate(
            @Qualifier("sqlSessionFactorySec") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
