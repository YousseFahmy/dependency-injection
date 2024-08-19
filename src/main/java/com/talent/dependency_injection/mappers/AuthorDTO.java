package com.talent.dependency_injection.mappers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class AuthorDTO {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

}
