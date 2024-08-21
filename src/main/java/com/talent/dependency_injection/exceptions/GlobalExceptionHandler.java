package com.talent.dependency_injection.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({CourseDoesNotExistException.class})
    public ResponseEntity<Object> handleCourseDoesNotExistException(CourseDoesNotExistException exception){
        return ResponseEntity.badRequest()
                    .body(exception.getMessage());
    }

    @ExceptionHandler({CourseAlreadyExistsException.class})
    public ResponseEntity<Object> handleCourseAlreadyExistsException(CourseAlreadyExistsException exception){
        return ResponseEntity.badRequest()
                    .body(exception.getMessage());
    }

    @ExceptionHandler({AuthorDoesNotExistException.class})
    public ResponseEntity<Object> handleAuthorDoesNotExistException(AuthorDoesNotExistException exception){
        return ResponseEntity.badRequest()
                    .body(exception.getMessage());
    }

    @ExceptionHandler({AuthorAlreadyExistsException.class})
    public ResponseEntity<Object> handleAuthorAlreadyExistsException(AuthorAlreadyExistsException exception){
        return ResponseEntity.badRequest()
                    .body(exception.getMessage());
    }
    
}
