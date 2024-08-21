package com.talent.dependency_injection.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talent.dependency_injection.entities.Author;
import com.talent.dependency_injection.exceptions.AuthorAlreadyExistsException;
import com.talent.dependency_injection.exceptions.AuthorDoesNotExistException;
import com.talent.dependency_injection.mappers.AuthorDTO;
import com.talent.dependency_injection.mappers.AuthorMapper;
import com.talent.dependency_injection.repositories.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorMapper authorMapper;

    public AuthorDTO findById(int id){
        Author foundAuthor = authorRepository.findById(id).orElseThrow(() -> new AuthorDoesNotExistException(String.format("Author with ID %d does not exist", id)));
        return authorMapper.mapToAuthorDTO(foundAuthor);
    }

    public AuthorDTO findByEmail(String email){
        Author foundAuthor = authorRepository.findByEmail(email).orElseThrow(() -> new AuthorDoesNotExistException(String.format("Author with email %s does not exist", email)));
        return authorMapper.mapToAuthorDTO(foundAuthor);
    }

    public AuthorDTO addAuthor(AuthorDTO authorDTO){
        Author author = authorMapper.mapToAuthor(authorDTO);

        boolean authorExists = authorRepository.existsById(author.getId()).orElse(false);
        if(authorExists){
            throw new AuthorAlreadyExistsException(String.format("Author with ID %d already exists", author.getId()));
        }

        Author savedAuthor = authorRepository.save(author);
        return authorMapper.mapToAuthorDTO(savedAuthor);
    }
}
