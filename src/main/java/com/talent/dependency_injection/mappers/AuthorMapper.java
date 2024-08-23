package com.talent.dependency_injection.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.talent.dependency_injection.entities.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO mapToAuthorDTO(Author author);

    @Mapping(target = "courses", ignore = true)
    Author mapToAuthor(AuthorDTO authorDTO);
}
