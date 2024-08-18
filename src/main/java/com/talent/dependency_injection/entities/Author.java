package com.talent.dependency_injection.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    
    @Column(length = 20, nullable = false)
    @Getter
    private String name;
    
    @Column(length = 50, nullable = false)
    @Getter
    private String email;
    
    @Temporal(TemporalType.DATE)
    @Getter
    private String birthdate;
}
