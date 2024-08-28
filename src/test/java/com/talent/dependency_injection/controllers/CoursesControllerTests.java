package com.talent.dependency_injection.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.dependency_injection.exceptions.CourseAlreadyExistsException;
import com.talent.dependency_injection.exceptions.CourseDoesNotExistException;
import com.talent.dependency_injection.exceptions.GlobalExceptionHandler;
import com.talent.dependency_injection.mappers.CourseDTO;
import com.talent.dependency_injection.recommenders.CourseRecommender;
import com.talent.dependency_injection.security.HeaderAuthentication;
import com.talent.dependency_injection.services.CourseService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(controllers = CourseController.class, 
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ContextConfiguration(classes = {CourseController.class})
public class CoursesControllerTests {

    @MockBean
    private CourseService courseService;

    @MockBean
    private GlobalExceptionHandler globalExceptionHandler;

    @MockBean
    private CourseRecommender courseRecommender;

    @InjectMocks
    private CourseController courseController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CourseDTO course;

    @BeforeEach
    public void setup() {
        this.course = CourseDTO.builder()
                .id(1)
                .name("Testing Course")
                .description("Testing 101")
                .build();

        when(globalExceptionHandler.handleCourseDoesNotExistException(
                any(CourseDoesNotExistException.class))).thenReturn(ResponseEntity.badRequest().build());

        when(globalExceptionHandler.handleCourseAlreadyExistsException(
                any(CourseAlreadyExistsException.class))).thenReturn(ResponseEntity.badRequest().build());

        SecurityContextHolder.getContext().setAuthentication(new HeaderAuthentication());
    }

    @Test
    public void getCourseById_AuthorExists_Ok() throws Exception {
        when(courseService.findById(1)).thenReturn(course);

        ResultActions response = mockMvc.perform(get("/courses/{id}", course.getId()));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(course.getId())))
                .andExpect(jsonPath("$.name", is(course.getName())))
                .andExpect(jsonPath("$.description", is(course.getDescription())));

        verify(courseService).findById(course.getId());
    }

    @Test
    public void getCourseById_DoesNotExist_BadRequest() throws Exception {
        when(courseService.findById(anyInt())).thenThrow(CourseDoesNotExistException.class);

        ResultActions response = mockMvc.perform(get("/courses/{id}", course.getId()));

        response.andExpect(status().isBadRequest());

        verify(courseService).findById(course.getId());
    }

    @Test
    public void addCourse_DoesNotExist_Ok() throws Exception {
        when(courseService.addCourse(any(CourseDTO.class))).thenReturn(course);

        ResultActions response = mockMvc.perform(post("/courses/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)));

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(course)));

    }

    @Test
    public void addCourse_CourseExists_BadRequest() throws Exception {
        when(courseService.addCourse(any(CourseDTO.class)))
                .thenThrow(CourseAlreadyExistsException.class);

        ResultActions response = mockMvc.perform(post("/courses/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)));

        response.andExpect(status().isBadRequest());
    }

    @Test
    public void deleteCourse_CourseExists_Ok() throws Exception {
        doNothing().when(courseService).deleteById(anyInt());
        
        ResultActions response = mockMvc.perform(delete("/courses/{id}", course.getId())
        .contentType(MediaType.APPLICATION_JSON));
        
        response.andExpect(status().isOk());
        
        verify(courseService, times(1)).deleteById(course.getId());
    }
    
    @Test
    public void deleteCourse_CourseDoesNotExist_BadRequest() throws Exception {
        doThrow(CourseDoesNotExistException.class).when(courseService).deleteById(anyInt());

        ResultActions response = mockMvc.perform(delete("/courses/{id}", course.getId())
                .contentType(MediaType.APPLICATION_JSON));
        
        response.andExpect(status().isBadRequest());

        verify(courseService, times(1)).deleteById(course.getId());
    }

    @Test
    public void updateCourse_CourseExists_Ok() throws Exception{
        when(courseService.updateCourse(any(CourseDTO.class))).thenReturn(course);

        ResultActions response = mockMvc.perform(put("/courses/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)));
        
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(courseService, times(1)).updateCourse(any());
    }

    @Test
    public void updateCourse_CourseDoesNotExists_BadRequest() throws Exception{
        when(courseService.updateCourse(any(CourseDTO.class)))
                .thenThrow(CourseDoesNotExistException.class);

        ResultActions response = mockMvc.perform(put("/courses/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(course)));
        
        response.andExpect(status().isBadRequest());

        verify(courseService, times(1)).updateCourse(any());
    }

    @Test
    public void paginateCourses_Ok() throws Exception {
        when(courseService.paginateCourses(any())).thenReturn(new ArrayList<CourseDTO>());

        Map<String, Integer> reqBody = new HashMap<>();
        reqBody.put("page", 0);
        reqBody.put("size", 5);

        ResultActions response = mockMvc.perform(get("/courses/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reqBody)));

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(courseService, times(1)).paginateCourses(any());
    }

    @Test
    public void recommendCourses_Ok() throws Exception{
        when(courseService.getRecommendedCourses()).thenReturn(new ArrayList<CourseDTO>());

        ResultActions response = mockMvc.perform(get("/courses/recommend"));

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}