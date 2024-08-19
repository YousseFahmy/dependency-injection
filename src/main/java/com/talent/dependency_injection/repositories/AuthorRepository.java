package com.talent.dependency_injection.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talent.dependency_injection.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findbyEmail(String email);
    
}
