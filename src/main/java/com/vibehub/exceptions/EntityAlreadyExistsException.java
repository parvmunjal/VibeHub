package com.vibehub.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(){
        super("Entity already exists!");
    }
}
