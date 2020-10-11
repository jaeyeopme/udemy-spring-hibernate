package com.spring.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception e) {
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customerErrorResponse.setMessage(e.getMessage());
        customerErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(customerErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException e) {
        CustomerErrorResponse customerErrorResponse = new CustomerErrorResponse();
        customerErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        customerErrorResponse.setMessage(e.getMessage());
        customerErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(customerErrorResponse, HttpStatus.NOT_FOUND);
    }

}
