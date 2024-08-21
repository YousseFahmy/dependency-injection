package com.talent.dependency_injection.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talent.dependency_injection.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

    // Optional<Course findById(int id);

    Optional<Boolean> existsById(int id);
    
}