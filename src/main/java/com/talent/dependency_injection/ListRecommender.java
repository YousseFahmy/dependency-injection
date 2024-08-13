package com.talent.dependency_injection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ListRecommender implements CourseRecommender {

    @Override
    public List<Course> recommendCourses() {
        ArrayList<Course> mockList = new ArrayList<>();
        Course mockCourse = new Course("Lists 101");
        mockList.add(mockCourse);
        return mockList;
    }

}
