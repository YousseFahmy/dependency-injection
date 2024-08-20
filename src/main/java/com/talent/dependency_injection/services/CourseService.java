package com.talent.dependency_injection.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.talent.dependency_injection.entities.Course;
import com.talent.dependency_injection.mappers.CourseDTO;
import com.talent.dependency_injection.mappers.CourseMapper;
import com.talent.dependency_injection.recommenders.CourseRecommender;
import com.talent.dependency_injection.repositories.CourseRepository;

@Service
public class CourseService {

    @Autowired
    @Qualifier("alphaRecommender")
    CourseRecommender courseRecommender; 

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;

    public List<Course> getRecommendedCourses(){
        return courseRecommender.recommendCourses();
    }

    public List<CourseDTO> paginateCourses(Pageable pageable){
        Page<Course> p = courseRepository.findAll(pageable);
        return p.stream().map(courseMapper::mapToCourseDTO).collect(Collectors.toList());
    }
}
