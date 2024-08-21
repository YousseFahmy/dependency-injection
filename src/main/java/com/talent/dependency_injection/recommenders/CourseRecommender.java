package com.talent.dependency_injection.recommenders;

import java.util.List;

import com.talent.dependency_injection.mappers.CourseDTO;

public interface CourseRecommender {
    List<CourseDTO> recommendCourses();
}
