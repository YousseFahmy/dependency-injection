package com.talent.dependency_injection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.talent.dependency_injection.recommenders.CourseRecommender;
import com.talent.dependency_injection.recommenders.ListRecommender;
import com.talent.dependency_injection.recommenders.StackRecommender;

@Configuration
public class CourseRecommenderConfig {
    
    @Bean
    // @Primary
    public CourseRecommender primaryRecommender(){
        return new ListRecommender();
    }
    
    @Bean
    public StackRecommender secondaryRecommender(){
        return new StackRecommender();
    }

}
