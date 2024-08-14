package com.talent.dependency_injection.recommenders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.talent.dependency_injection.entities.Course;

// @Component
public class ListRecommender implements CourseRecommender {

    @Override
    public List<Course> recommendCourses() {
        ArrayList<Course> mockList = new ArrayList<>();
        Course mockCourse = new Course(1, 
            "Lists 101", 
            "A course on lists", 
            6);
        mockList.add(mockCourse);
        return mockList;
    }

}
