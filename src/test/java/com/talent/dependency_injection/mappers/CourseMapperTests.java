package com.talent.dependency_injection.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talent.dependency_injection.entities.Course;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest
public class CourseMapperTests {

    @Autowired
    private CourseMapper courseMapper;

    private Course course;
    private CourseDTO dto;

    @BeforeEach
    public void setup(){
        this.course = Course.builder()
                        .id(1)
                        .name("Testing Course")
                        .description("Testing 101")
                        .credit(3)
                        .build();
                            
        this.dto = CourseDTO.builder()
                    .id(1)
                    .name("Testing Course")
                    .description("Testing 101")
                    .credit(3)
                    .build();
    }

    @Test
    public void ObjectToDTO(){
        CourseDTO mappedDTO = courseMapper.mapToCourseDTO(course);

        assertEquals(dto, mappedDTO);
    }

    @Test
    public void DTOToObject(){
        Course mappedCourse = courseMapper.mapToCourse(dto);

        assertEquals(course, mappedCourse);
    }

    @Test
    public void NullToObject(){
        Course mappedCourse = courseMapper.mapToCourse(null);

        assertNull(mappedCourse);
    }
    
    @Test
    public void NullToDTO(){
        CourseDTO mappedCourse = courseMapper.mapToCourseDTO(null);

        assertNull(mappedCourse);
    }
    
}
