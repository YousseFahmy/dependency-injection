package com.talent.dependency_injection.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "author")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @Getter
    private int id;
    
    @Column(length = 20)
    @NotNull
    @Getter @Setter
    private String name;
    
    @Column(length = 50)
    @NotNull
    @Getter @Setter
    private String email;
    
    @Temporal(TemporalType.DATE)
    @Getter @Setter
    @NotNull
    private String birthdate;

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;
}
