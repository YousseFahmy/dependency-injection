package com.talent.dependency_injection.recommenders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.talent.dependency_injection.entities.Course;
import com.talent.dependency_injection.repositories.CourseRepository;

public class AlphaRecommender implements CourseRecommender {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> recommendCourses() {
        List<Course> courses = courseRepository.findAll();
        courses.sort(null);
        return courses.subList(0, 5);
    }
    
}
