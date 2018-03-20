package com.example.helloboot.springBoot.db.dataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class SecondaryDataSourceConfig{

    @Bean(name = "dataSourceSec")
    @ConfigurationProperties(prefix = "spring.datasource.secondary.hikari")
    public DataSource secondaryDataSource(DataSourceProperties properties){
        return DataSourceBuilder.create(properties.getClassLoader())
                .type(HikariDataSource.class)
                .driverClassName(properties.determineDriverClassName())
                .username(properties.determineUsername())
                .password(properties.determinePassword())
                .url(properties.determineUrl())
                .build();
    }

    @Bean(name = "jdbcTemplateSec")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("dataSourceSec") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
