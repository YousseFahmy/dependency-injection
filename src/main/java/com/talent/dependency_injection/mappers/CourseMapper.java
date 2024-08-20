package com.talent.dependency_injection.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.talent.dependency_injection.entities.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO mapToCourseDTO(Course course);

    @Mapping(target = "assessment", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "authors", ignore = true)
    Course mapToCourse(CourseDTO courseDTO);
}
