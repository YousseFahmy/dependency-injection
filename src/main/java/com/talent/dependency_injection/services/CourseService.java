package com.talent.dependency_injection.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.talent.dependency_injection.entities.Course;
import com.talent.dependency_injection.exceptions.CourseAlreadyExistsException;
import com.talent.dependency_injection.exceptions.CourseDoesNotExistException;
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

    public List<CourseDTO> getRecommendedCourses(){
        return courseRecommender.recommendCourses();
    }

    public List<CourseDTO> paginateCourses(Pageable pageable){
        Page<Course> p = courseRepository.findAll(pageable);
        return p.stream().map(courseMapper::mapToCourseDTO).collect(Collectors.toList());
    }

    public CourseDTO findById(int id){
        Course foundCourse = courseRepository.findById(id)
        .orElseThrow(() -> new CourseDoesNotExistException(String.format("Course with id %d does not exist", id)));

        return courseMapper.mapToCourseDTO(foundCourse);
    }

    public CourseDTO addCourse(CourseDTO courseDTO){
        Course course = courseMapper.mapToCourse(courseDTO);

        boolean courseExists = courseRepository.existsById(course.getId()).orElse(false);
        if(courseExists){
            throw new CourseAlreadyExistsException(String.format("Course with ID %d already exists", course.getId()));
        }

        Course savedCourse = courseRepository.save(course);
        return courseMapper.mapToCourseDTO(savedCourse);
    }

    public CourseDTO updateCourse(CourseDTO courseDTO){
        Course course = courseMapper.mapToCourse(courseDTO);

        courseRepository.existsById(course.getId())
            .orElseThrow(() -> new CourseDoesNotExistException(String.format("Course with ID %d does not exist", course.getId())));

        Course updatedCourse = courseRepository.save(course);
        return courseMapper.mapToCourseDTO(updatedCourse);
    }

    public void deleteById(int id){
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new CourseDoesNotExistException(String.format("Course with ID %d does not exist", id)));
        courseRepository.delete(course);
    }
}
