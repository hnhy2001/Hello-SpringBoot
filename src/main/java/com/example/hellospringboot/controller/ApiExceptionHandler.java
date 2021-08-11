package com.example.hellospringboot.controller;

import com.example.hellospringboot.exceptions.ErrorMessage;

import com.example.hellospringboot.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;

public class ApiExceptionHandler {

    //Xử lí authenticationException
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage LoginException(NotFoundException e, WebRequest request){
        return new ErrorMessage(404, e.getMessage());
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorMessage TodoBadRequest(MethodNotAllowedException e, WebRequest request){
        return new ErrorMessage(405,"looix");
    }
}
