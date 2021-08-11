package com.example.hellospringboot.exceptions;

public class FindNotFound extends RuntimeException{
    public FindNotFound(String mess){
        super(mess);
    }
}
