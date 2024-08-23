package com.talent.dependency_injection.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.talent.dependency_injection.mappers.CourseDTO;
import com.talent.dependency_injection.recommenders.CourseRecommender;
import com.talent.dependency_injection.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseRecommender courseRecommender;

    @GetMapping("/recommend")
    public ResponseEntity<List<CourseDTO>> recommendCourses(){
        List<CourseDTO> courses =  courseService.getRecommendedCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable int courseId){
        CourseDTO courseDTO = courseService.findById(courseId);
        return ResponseEntity.ok().body(courseDTO);
    }

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable int courseId){
        courseService.deleteById(courseId);
    }
    
    @PutMapping("/")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO updatedCourseDTO){
        CourseDTO savedCourse = courseService.updateCourse(updatedCourseDTO);
        return ResponseEntity.ok(savedCourse);
    }

    @PostMapping("/")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO newCourseDTO){
        CourseDTO savedCourseDTO = courseService.addCourse(newCourseDTO);
        return ResponseEntity.ok(savedCourseDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> paginateCourses(@RequestBody Map<String, Integer> reqBody){
        int pageNumber = reqBody.get("page");
        int pageSize = reqBody.get("size");

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CourseDTO> courses = courseService.paginateCourses(pageable);
        return ResponseEntity.ok(courses);
    }
}
