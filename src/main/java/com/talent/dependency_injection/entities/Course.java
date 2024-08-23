package com.talent.dependency_injection.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course implements Comparable<Course>{
    @Id
    @Getter
    private int id;

    @Getter
    @Column(length = 50)
    @NotNull
    private String name;
    
    @Getter
    @Column(length = 200)
    @NotNull
    private String description;
    
    @Getter
    @Column(nullable = false)
    private int credit;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "courseAuthor", 
        joinColumns = @JoinColumn(name = "courseId"),
        inverseJoinColumns = @JoinColumn(name = "authorId")
    )
    private List<Author> authors;
    
    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "assessmentId")
    private Assessment assessment;

    @Getter @Setter
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Rating> ratings; 

    @Override
    public int compareTo(Course otherCourse) {
        return this.name.compareTo(otherCourse.name);
    }
}
