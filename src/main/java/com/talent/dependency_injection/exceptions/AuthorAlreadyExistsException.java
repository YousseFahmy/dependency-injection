package com.talent.dependency_injection.exceptions;

public class AuthorAlreadyExistsException extends CourseAppException {

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
    
}
