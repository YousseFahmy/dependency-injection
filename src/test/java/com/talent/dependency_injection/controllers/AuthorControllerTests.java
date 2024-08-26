package com.talent.dependency_injection.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talent.dependency_injection.exceptions.AuthorAlreadyExistsException;
import com.talent.dependency_injection.exceptions.AuthorDoesNotExistException;
import com.talent.dependency_injection.exceptions.GlobalExceptionHandler;
import com.talent.dependency_injection.mappers.AuthorDTO;
import com.talent.dependency_injection.services.AuthorService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(controllers = AuthorController.class,
    excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ContextConfiguration(classes = AuthorController.class)
public class AuthorControllerTests {
    
    @MockBean
    private AuthorService authorService;

    @MockBean
    private GlobalExceptionHandler globalExceptionHandler;
    
    @InjectMocks
    private AuthorController authorController;
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private AuthorDTO author;
    
    @BeforeEach
    public void setup(){
        this.author = AuthorDTO.builder()
                            .id(1)
                            .name("Testing Author")
                            .email("author@test.com")
                            .build();

        when(globalExceptionHandler.handleAuthorDoesNotExistException(
            any(AuthorDoesNotExistException.class)
            )).thenReturn(ResponseEntity.badRequest().build());

            when(globalExceptionHandler.handleAuthorAlreadyExistsException(
            any(AuthorAlreadyExistsException.class)
            )).thenReturn(ResponseEntity.badRequest().build());
    }

    @Test
    public void getAuthorById_AuthorExists_Succeeds() throws Exception{
        when(authorService.findById(anyInt())).thenReturn(author);

        ResultActions response = mockMvc.perform(get("/author/{id}", author.getId()));

        response.andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(author.getId())))
            .andExpect(jsonPath("$.name", is(author.getName())))
            .andExpect(jsonPath("$.email", is(author.getEmail())));
                
        verify(authorService).findById(author.getId());
    }

    @Test
    public void getAuthorById_DoesNotExist_BadRequest() throws Exception{
        when(authorService.findById(anyInt())).thenThrow(AuthorDoesNotExistException.class);

        ResultActions response = mockMvc.perform(get("/author/{id}", author.getId()));
        
        response.andExpect(status().isBadRequest());
                
        verify(authorService).findById(author.getId());
    }

    @Test
    public void getAuthorByEmail_AuthorExists_Succeeds() throws Exception{
        when(authorService.findByEmail(anyString())).thenReturn(author);

        ResultActions response = mockMvc.perform(get("/author").param("email", author.getEmail()));
        
        response.andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(author.getId())))
            .andExpect(jsonPath("$.name", is(author.getName())))
            .andExpect(jsonPath("$.email", is(author.getEmail())));
                
        verify(authorService).findByEmail(author.getEmail());
    }

    @Test
    public void getAuthorByEmail_DoesNotExist_BadRequest() throws Exception{
        when(authorService.findByEmail(anyString())).thenThrow(AuthorDoesNotExistException.class);
        
        ResultActions response = mockMvc.perform(get("/author").param("email", author.getEmail()));
        
        response.andExpect(status().isBadRequest());
                
        verify(authorService).findByEmail(author.getEmail());
    }

    @Test
    public void addAuthor_DoesNotExist_Suceeds() throws Exception{
        when(authorService.addAuthor(any(AuthorDTO.class))).thenReturn(author);

        ResultActions response = mockMvc.perform(post("/author")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(author)));

        response.andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(author)));

    }

    @Test
    public void addAuthor_AuthorExists_Suceeds() throws Exception{
        when(authorService.addAuthor(any(AuthorDTO.class)))
            .thenThrow(AuthorAlreadyExistsException.class);

        ResultActions response = mockMvc.perform(post("/author")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(author)));

        response.andExpect(status().isBadRequest());
    }
}