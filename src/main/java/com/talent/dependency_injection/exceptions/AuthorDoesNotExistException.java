package com.talent.dependency_injection.exceptions;

public class AuthorDoesNotExistException extends CourseAppException {

    public AuthorDoesNotExistException(String message) {
        super(message);
    }
    
}
