package com.talent.dependency_injection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talent.dependency_injection.mappers.AuthorDTO;
import com.talent.dependency_injection.mappers.AuthorMapper;
import com.talent.dependency_injection.services.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorMapper authorMapper;

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable int authorId){
        AuthorDTO author = authorService.findById(authorId);
        return ResponseEntity.ok(author);
    }

    @GetMapping()
    public ResponseEntity<AuthorDTO> getAuthorByEmail(@RequestParam String email){
        AuthorDTO author = authorService.findByEmail(email);
        return ResponseEntity.ok(author);
    }

    @PostMapping()
    public ResponseEntity<AuthorDTO> addAuthor(AuthorDTO author){
        AuthorDTO savedAuthor = authorService.addAuthor(author);
        return ResponseEntity.ok(savedAuthor);
    }
}
