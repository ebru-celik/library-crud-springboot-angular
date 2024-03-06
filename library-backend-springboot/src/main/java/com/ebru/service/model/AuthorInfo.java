package com.ebru.service.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorInfo {
    private String firstName;
    private String lastName;

    public AuthorInfo (String firstname, String lastname){
        this.firstName = firstname;
        this.lastName = lastname;
    }
}
