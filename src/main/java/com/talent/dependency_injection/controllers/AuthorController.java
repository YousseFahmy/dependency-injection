package com.talent.dependency_injection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talent.dependency_injection.entities.Author;
import com.talent.dependency_injection.services.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable int authorId){
        Author author = authorService.findById(authorId);
        return ResponseEntity.ok().body(author);
    }

    @GetMapping()
    public ResponseEntity<Author> getAuthorByEmail(@RequestParam String email){
        Author author = authorService.findByEmail(email);
        return ResponseEntity.ok().body(author);
    }
}
