package com.talent.dependency_injection.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "course")
public class Course implements Comparable<Course>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    @Column(length = 50, nullable = false)
    private String name;
    
    @Getter
    @Column(length = 200, nullable = false)
    private String description;
    
    @Getter
    @Column(nullable = false)
    private int credit;
    
    @Getter @Setter
    private int assessmentId;

    public Course (int id, String name, String description, int credit){
        this.id = id;
        this.name = name;
        this.description = description;
        this.credit = credit;
    }

    @Override
    public int compareTo(Course otherCourse) {
        return this.name.compareTo(otherCourse.name);
    }
}
