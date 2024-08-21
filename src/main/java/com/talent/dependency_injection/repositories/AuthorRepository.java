package com.talent.dependency_injection.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talent.dependency_injection.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Boolean> existsById(int id);

    Optional<Author> findByEmail(String email);
    
}
