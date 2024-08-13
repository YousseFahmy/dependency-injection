package com.talent.dependency_injection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CourseRecommenderConfig {
    
    @Bean
    @Primary
    public CourseRecommender primaryRecommender(){
        return new ListRecommender();
    }
    
    @Bean
    public CourseRecommender secondaryRecommender(){
        return new StackRecommender();
    }

}
