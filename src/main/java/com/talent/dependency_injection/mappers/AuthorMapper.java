package com.talent.dependency_injection.mappers;

import org.mapstruct.Mapper;

import com.talent.dependency_injection.entities.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO mapToAuthorDTO(Author author);

    Author mapToAuthor(AuthorDTO authorDTO);
    
}
