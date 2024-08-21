package com.talent.dependency_injection.recommenders;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.talent.dependency_injection.entities.Course;
import com.talent.dependency_injection.mappers.CourseDTO;
import com.talent.dependency_injection.mappers.CourseMapper;
import com.talent.dependency_injection.repositories.CourseRepository;

@Component
public class AlphaRecommender implements CourseRecommender {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<CourseDTO> recommendCourses() {
        Pageable page = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")));
        Page<Course> courses = courseRepository.findAll(page);
        return courses.stream().map(courseMapper::mapToCourseDTO).collect(Collectors.toList());
    }
    
}
