package com.talent.dependency_injection.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    
    @Column
    @NotNull
    @Getter
    @OneToOne(mappedBy = "assessment")
    private Course courseId;
    
    @Column(length = 100)
    @NotNull
    @Getter
    private String content;
}
