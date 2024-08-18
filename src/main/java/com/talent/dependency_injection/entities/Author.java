package com.talent.dependency_injection.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "author")
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    
    @Column(length = 20)
    @NotNull
    @Getter
    private String name;
    
    @Column(length = 50)
    @NotNull
    @Getter
    private String email;
    
    @Temporal(TemporalType.DATE)
    @Getter
    @NotNull
    private String birthdate;

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;
}
