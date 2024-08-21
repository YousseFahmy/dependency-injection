package com.talent.dependency_injection.mappers;

import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@ToString
@Builder
@AllArgsConstructor
public class CourseDTO {
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;
}
