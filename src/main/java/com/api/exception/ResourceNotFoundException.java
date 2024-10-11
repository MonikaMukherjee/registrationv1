package com.api.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg) {
        super(msg); //super keyword calling constructor of parentclass,msg display in postman

    }
}
