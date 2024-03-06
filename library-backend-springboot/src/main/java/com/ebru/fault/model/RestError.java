package com.ebru.fault.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestError {

    private String code;
    private String description;

    public RestError(String code, String des){
        this.code = code;
        this.description = des;
    }
}
