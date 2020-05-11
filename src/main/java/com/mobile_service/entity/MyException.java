package com.mobile_service.entity;

public class MyException extends Exception {
    private String message;
    public MyException(String string) {
        message = string;
    }
    public String printMessage(){
        return message;
    }
}
