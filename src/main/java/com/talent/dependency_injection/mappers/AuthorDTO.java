package com.talent.dependency_injection.mappers;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthorDTO {
    private int id;

    private String name;

    private String email;

    private String birthdate;

}
