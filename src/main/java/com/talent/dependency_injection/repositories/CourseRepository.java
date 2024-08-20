package com.talent.dependency_injection.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talent.dependency_injection.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

    Course findById(int id);

    @Override
    List<Course> findAll();
    
}