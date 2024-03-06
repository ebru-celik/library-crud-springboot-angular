package com.ebru.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthorRestRequest implements Serializable {
    @NotBlank (message = "Firstname can not be blank")
    private String firstName;
    @NotBlank (message = "Lastname can not be blank")
    private String lastName;
}
