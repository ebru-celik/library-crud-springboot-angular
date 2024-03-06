package com.ebru.fault;

import com.ebru.fault.exception.BusinessException;
import com.ebru.fault.exception.RestException;
import com.ebru.fault.model.RestError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler (RestException.class)
    public ResponseEntity<RestError> handleRestException(RestException re){
        return ResponseEntity.badRequest().body(new RestError("1000", re.getMessage()));
    }

    @ExceptionHandler (BusinessException.class)
    public ResponseEntity<RestError> handleBusinessException(BusinessException be){
        return ResponseEntity.badRequest().body(new RestError("5000", be.getMessage()));
    }

}
