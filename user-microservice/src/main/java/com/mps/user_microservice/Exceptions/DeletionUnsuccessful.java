package com.mps.user_microservice.Exceptions;

public class DeletionUnsuccessful extends RuntimeException {
    public DeletionUnsuccessful(){
        super("Deletion unsuccessful.");
    }
}
