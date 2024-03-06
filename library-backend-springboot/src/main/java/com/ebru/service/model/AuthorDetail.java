package com.ebru.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorDetail {
    private Long id;
    private String firstName;
    private String lastName;
}
