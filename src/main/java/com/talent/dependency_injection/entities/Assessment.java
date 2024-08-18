package com.talent.dependency_injection.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    
    @Column(nullable = false)
    @Getter
    private int courseId;
    
    @Column(length = 100, nullable = false)
    @Getter
    private String content;
}
