package com.talent.dependency_injection.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.talent.dependency_injection.entities.Author;

@SpringBootTest
public class AuthorMapperTests {
    @Autowired
    private AuthorMapper authorMapper;

    private Author author;
    private AuthorDTO dto;

    @BeforeEach
    public void setup(){
        this.author = Author.builder()
                        .id(1)
                        .name("Testing Author")
                        .email("tester@email.com")
                        .birthdate("12-12-2024")
                        .build();
                            
        this.dto = AuthorDTO.builder()
                    .id(1)
                    .name("Testing Author")
                    .email("tester@email.com")
                    .birthdate("12-12-2024")
                    .build();
    }

    @Test
    public void ObjectToDTO(){
        AuthorDTO mappedDTO = authorMapper.mapToAuthorDTO(author);

        assertEquals(dto, mappedDTO);
    }

    @Test
    public void DTOToObject(){
        Author mappedAuthor = authorMapper.mapToAuthor(dto);

        assertEquals(author, mappedAuthor);
    }

    @Test
    public void NullToObject(){
        Author mappedAuthor = authorMapper.mapToAuthor(null);

        assertNull(mappedAuthor);
    }
    
    @Test
    public void NullToDTO(){
        AuthorDTO mappedAuthor = authorMapper.mapToAuthorDTO(null);

        assertNull(mappedAuthor);
    }
}
