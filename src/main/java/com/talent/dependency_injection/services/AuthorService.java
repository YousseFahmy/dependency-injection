package com.talent.dependency_injection.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.dependency_injection.entities.Author;
import com.talent.dependency_injection.exceptions.AuthorAlreadyExistsException;
import com.talent.dependency_injection.exceptions.AuthorDoesNotExistException;
import com.talent.dependency_injection.repositories.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author findById(int id){
        return authorRepository.findById(id).orElseThrow(() -> new AuthorDoesNotExistException(String.format("Author with ID %d does not exist", id)));
            
    }

    public Author findByEmail(String email){
        return authorRepository.findByEmail(email).orElseThrow(() -> new AuthorDoesNotExistException(String.format("Author with email %s does not exist", email)));
    }

    public Author addCourse(Author author){
        boolean authorExists = authorRepository.existsById(author.getId()).orElse(false);
        if(authorExists){
            throw new AuthorAlreadyExistsException(String.format("Author with ID %d already exists", author.getId()));
        }

        return authorRepository.save(author);
    }
}
