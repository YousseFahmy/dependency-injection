package com.talent.dependency_injection.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.talent.dependency_injection.recommenders.AlphaRecommender;
import com.talent.dependency_injection.recommenders.CourseRecommender;
import com.talent.dependency_injection.recommenders.ListRecommender;
import com.talent.dependency_injection.recommenders.StackRecommender;

@Configuration
public class CourseRecommenderConfig {
    
    @Bean
    // @Primary
    public CourseRecommender alphaRecommender(){
        return new AlphaRecommender();
    }
    
    @Bean
    public CourseRecommender secondaryRecommender(){
        return new StackRecommender();
    }

    @Bean
    DataSource datasource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        // dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/TalentProgram?currentSchema=public");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("postgres");
        return dataSourceBuilder.build();
    }

}
