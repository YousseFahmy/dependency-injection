package com.talent.dependency_injection.mappers;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CourseDTO {
    private int id;

    private String name;

    private String description;

    private int credit;
}
