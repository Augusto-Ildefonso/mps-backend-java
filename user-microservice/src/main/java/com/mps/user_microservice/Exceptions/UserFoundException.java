package com.mps.user_microservice.Exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("User already on the database.");
    }
}
