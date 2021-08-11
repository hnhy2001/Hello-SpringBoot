package com.example.hellospringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiException {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage LoginNotFoundException(NotFoundException e, WebRequest request){
        return new ErrorMessage(404, e.getMessage());
    }

    @ExceptionHandler({FindNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage FindNotFoundException(FindNotFound fe, WebRequest request){
        return new ErrorMessage(404, fe.getMessage());
    }

}
