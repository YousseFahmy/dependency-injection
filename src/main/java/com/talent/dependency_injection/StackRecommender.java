package com.talent.dependency_injection;

import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Component;

@Component
public class StackRecommender implements CourseRecommender{

    @Override
    public List<Course> recommendCourses() {
        Stack<Course> mockList = new Stack<>();
        Course mockCourse = new Course("Stacks 101");
        mockList.add(mockCourse);
        return mockList;
    }

}
