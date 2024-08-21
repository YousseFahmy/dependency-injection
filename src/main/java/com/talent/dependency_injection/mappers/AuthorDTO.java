package com.talent.dependency_injection.mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
public class AuthorDTO {
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

}
