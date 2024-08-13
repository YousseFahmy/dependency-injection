package com.talent.dependency_injection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    // @Qualifier("stackRecommender")
    CourseRecommender secondaryRecommender; 
    // Don't forget to remove @Primary for variable priority

    List<Course> getRecommendedCourses(){
        return secondaryRecommender.recommendCourses();
    }
}
