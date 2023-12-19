package com.theoahga.exception;

public class DistanceComputingException extends Exception{
    private final String message;
    public DistanceComputingException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
