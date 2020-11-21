package com.nokiatask;

public class ErrorLogNotFoundException extends Exception{
    public ErrorLogNotFoundException(){
        super();
    }

    public ErrorLogNotFoundException(String message){
        super(message);
    }
}
