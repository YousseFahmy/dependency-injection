package com.talent.dependency_injection.mappers;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@ToString
public class CourseDTO {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;
}
