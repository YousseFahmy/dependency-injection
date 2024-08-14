package com.talent.dependency_injection.recommenders;

import java.util.List;

import com.talent.dependency_injection.entities.Course;

public interface CourseRecommender {
    List<Course> recommendCourses();
}
