package com.example.hellospringboot.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mess){
        super(mess);
    }
}
