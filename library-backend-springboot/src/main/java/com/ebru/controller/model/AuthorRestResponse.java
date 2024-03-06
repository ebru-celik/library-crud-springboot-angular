package com.ebru.controller.model;

import com.ebru.service.model.AuthorDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AuthorRestResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;

    public AuthorRestResponse(AuthorDetail authorDetail) {
        this.id = authorDetail.getId();
        this.firstName = authorDetail.getFirstName();
        this.lastName = authorDetail.getLastName();
    }
}
