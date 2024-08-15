package com.talent.dependency_injection.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.talent.dependency_injection.entities.Course;
import com.talent.dependency_injection.recommenders.CourseRecommender;

@Service
public class CourseService {

    @Autowired
    @Qualifier("alphaRecommender")
    CourseRecommender courseRecommender; 

    public List<Course> getRecommendedCourses(){
        return courseRecommender.recommendCourses();
    }
}
