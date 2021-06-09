package edu.cs544.team5.exception;

import org.springframework.http.HttpStatus;

public class StudentHandleException extends RuntimeException {

    HttpStatus statusCode;
    public StudentHandleException(HttpStatus statusCode, String err){
        super(err);
        this.statusCode = statusCode;
    }
}
