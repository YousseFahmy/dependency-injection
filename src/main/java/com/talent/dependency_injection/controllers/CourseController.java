package com.talent.dependency_injection.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.talent.dependency_injection.entities.Course;
import com.talent.dependency_injection.recommenders.CourseRecommender;
import com.talent.dependency_injection.repositories.CourseRepository;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    @Qualifier("alphaRecommender")
    CourseRecommender courseRecommender;

    @GetMapping("/recommend")
    public ResponseEntity<List<Course>> recommendCourses(){
        List<Course> courses =  courseRecommender.recommendCourses();

        return ResponseEntity.ok().body(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable int courseId){
        Course course = courseRepository.getCourseByID(courseId);
        
        return ResponseEntity.ok().body(course);
    }

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable int courseId){
        courseRepository.deleteCourseById(courseId);
    }
    
    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void updateCourse(@RequestBody Course updatedCourse){
        courseRepository.updateCourse(updatedCourse);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void addCourse(@RequestBody Course newCourse){
        courseRepository.addCourse(newCourse);
    }
}
