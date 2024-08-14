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
    CourseRecommender secondaryRecommender; 
    
    // Don't forget to remove @Primary for variable priority
    // @Autowired
    // public CourseService(@Qualifier("secondaryRecommender") CourseRecommender inRecommender) {
    //     this.secondaryRecommender = inRecommender;
    // }

    public List<Course> getRecommendedCourses(){
        return secondaryRecommender.recommendCourses();
    }
    
    // @Autowired
    // @Qualifier("secondaryRecommender")
    // public void setSecondaryRecommender(CourseRecommender secondaryRecommender) {
    //     this.secondaryRecommender = secondaryRecommender;
    // }
}
