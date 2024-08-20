package com.talent.dependency_injection.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assessment")
@NoArgsConstructor
public class Assessment {
    @Id
    @Getter
    private int id;
    
    @NotNull
    @Getter
    @OneToOne(mappedBy = "assessment")
    private Course course;
    
    @Column(length = 100)
    @NotNull
    @Getter
    private String content;
}
