package com.talent.dependency_injection.recommenders;

import java.util.List;
import java.util.Stack;

import com.talent.dependency_injection.entities.Course;

public class StackRecommender implements CourseRecommender{

    @Override
    public List<Course> recommendCourses() {
        Stack<Course> mockList = new Stack<>();
        Course mockCourse = new Course(2, 
            "Stacks 101", 
            "A course on stacks", 
            6);
        mockList.add(mockCourse);
        return mockList;
    }

}
