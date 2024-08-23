package com.talent.dependency_injection.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.talent.dependency_injection.entities.Course;
import com.talent.dependency_injection.exceptions.CourseAlreadyExistsException;
import com.talent.dependency_injection.exceptions.CourseDoesNotExistException;
import com.talent.dependency_injection.mappers.CourseDTO;
import com.talent.dependency_injection.mappers.CourseMapper;
import com.talent.dependency_injection.recommenders.CourseRecommender;
import com.talent.dependency_injection.repositories.CourseRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CourseServiceTests {
    @Mock
    CourseRepository courseRepository;

    @Mock
    CourseMapper courseMapper;

    @Mock
    CourseRecommender courseRecommender;

    @InjectMocks
    CourseService courseService;

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
                    .build();

        when(courseMapper.mapToCourse(dto)).thenReturn(course);
        when(courseMapper.mapToCourseDTO(course)).thenReturn(dto);
    }

    @Test
    public void findById_CourseExists_ReturnsCourse(){
        when(courseRepository.findById(dto.getId())).thenReturn(Optional.of(course));

        courseService.findById(dto.getId());
        
        verify(courseRepository).findById(course.getId());
    }

    @Test
    public void findById_CourseDoesNotExist_ThrowsCourseDoesNotExistException(){
        assertThrows(CourseDoesNotExistException.class, 
            () -> courseService.findById(dto.getId()));
    }

    @Test
    public void addCourse_CourseDoesNotExist_ReturnsCourse(){
        when(courseRepository.existsById(dto.getId())).thenReturn(Optional.of(false));

        courseService.addCourse(dto);

        verify(courseRepository).save(course);
    }
    
    @Test
    public void addCourse_CourseExists_ThrowsCourseAlreadyExistsException(){
        when(courseRepository.existsById(dto.getId())).thenReturn(Optional.of(true));
        
        assertThrows(CourseAlreadyExistsException.class, 
            () -> courseService.addCourse(dto));
        
        verify(courseRepository, never()).save(course);
    }
    
    @Test
    public void updateCourse_CourseExists_ReturnsCourse(){
        when(courseRepository.existsById(dto.getId())).thenReturn(Optional.of(true));
        
        courseService.updateCourse(dto);
        
        verify(courseRepository).save(course);
    }

    @Test
    public void updateCourse_CourseDoesNotExist_ThrowsCourseDoesNotExistException(){
        assertThrows(CourseDoesNotExistException.class, 
            () -> courseService.updateCourse(dto));

        verify(courseRepository, never()).save(course);
    }

    @Test
    public void deleteById_CourseExists_Succeeds(){
        when(courseRepository.findById(dto.getId())).thenReturn(Optional.of(course));
        
        courseService.deleteById(dto.getId());
        
        verify(courseRepository).delete(course);
    }
    
    @Test
    public void deletebyId_CourseDoesNotExist_ThrowsCourseDoesNotExistException(){
        when(courseRepository.findById(dto.getId())).thenReturn(Optional.empty());

        assertThrows(CourseDoesNotExistException.class, 
            () -> courseService.deleteById(dto.getId()));

        verify(courseRepository, never()).delete(course);
    }

    @Test
    public void getRecommendedCourses_Succeeds(){
        when(courseRecommender.recommendCourses()).thenReturn(new ArrayList<CourseDTO>());

        courseService.getRecommendedCourses();

        verify(courseRecommender).recommendCourses();
    }

    @Test
    public void paginateCourses_Ok(){
        @SuppressWarnings("unchecked")
        Page<Course> mockPage = Mockito.mock(Page.class);
        Pageable mockPageable = Mockito.mock(Pageable.class);

        when(courseRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        courseService.paginateCourses(mockPageable);

        verify(courseRepository, times(1)).findAll(mockPageable);
    }
}
