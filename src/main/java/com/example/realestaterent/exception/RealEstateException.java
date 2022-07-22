package com.example.realestaterent.exception;

public class RealEstateException extends Exception{
    static String message = "Произошла ошибка";

    public RealEstateException() {
        super(message);
    }
}
