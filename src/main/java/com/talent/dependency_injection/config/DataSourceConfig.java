package com.talent.dependency_injection.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    DataSource datasource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/TalentProgram");
        dataSourceBuilder.username("dbUser");
        dataSourceBuilder.password("mysql");
        return dataSourceBuilder.build();
    }

}
