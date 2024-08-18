package com.talent.dependency_injection.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    
    @Getter
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
    
    @Column(nullable = false)
    @Getter
    private int number;
}
