package com.theoahga.exception;

public class IntensityUtilsException extends Exception{
    private final String message;
    public IntensityUtilsException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
