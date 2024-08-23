package com.talent.dependency_injection.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.talent.dependency_injection.entities.Author;
import com.talent.dependency_injection.exceptions.AuthorAlreadyExistsException;
import com.talent.dependency_injection.exceptions.AuthorDoesNotExistException;
import com.talent.dependency_injection.mappers.AuthorDTO;
import com.talent.dependency_injection.mappers.AuthorMapper;
import com.talent.dependency_injection.repositories.AuthorRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AuthorServiceTests {
    
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    private Author author;
    private AuthorDTO dto;

    @BeforeEach
    public void setup(){
        this.author = Author.builder()
                            .id(1)
                            .name("Testing Author")
                            .email("author@test.com")
                            .build();

        this.dto = AuthorDTO.builder()
                    .id(1)
                    .name("Testing Author")
                    .email("author@test.com")
                    .build();

        when(authorMapper.mapToAuthor(dto)).thenReturn(author);
        when(authorMapper.mapToAuthorDTO(author)).thenReturn(dto);
    }

    @Test
    public void addAuthor_AuthorDoesNotExist_ReturnsAuthor(){
        when(authorRepository.existsById(dto.getId())).thenReturn(Optional.of(false));

        authorService.addAuthor(dto);

        verify(authorRepository).save(author);
    }
    
    @Test
    public void addAuthor_AuthorExists_ThrowsAuthorAlreadyExistsException(){
        when(authorRepository.existsById(dto.getId())).thenReturn(Optional.of(true));
        
        assertThrows(AuthorAlreadyExistsException.class, 
            () -> authorService.addAuthor(dto));

        verify(authorRepository, never()).save(author);
    }

    @Test
    public void findById_AuthorExists_ReturnsAuthor(){
        when(authorRepository.findById(dto.getId())).thenReturn(Optional.of(author));

        authorService.findById(dto.getId());
        
        verify(authorRepository).findById(author.getId());
    }

    @Test
    public void findById_AuthorDoesNotExist_ThrowsAuthorDoesNotExistException(){
        assertThrows(AuthorDoesNotExistException.class, 
            () -> authorService.findById(dto.getId()));
    }

    @Test
    public void findByEmail_AuthorExists_ReturnsAuthor(){
        when(authorRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(author));

        authorService.findByEmail(dto.getEmail());
        
        verify(authorRepository).findByEmail(author.getEmail());
    }

    @Test
    public void findByEmail_AuthorDoesNotExist_ThrowsAuthorDoesNotExistException(){
        assertThrows(AuthorDoesNotExistException.class, 
            () -> authorService.findByEmail(dto.getEmail()));
    }

}
