package com.vibehub.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(){
        super("No entity exists!");
    }
    public EntityNotFoundException(String message){
        super("No "+message+" exists!");
    }
}
