package com.example.realestaterent.exception;

public class PropertyNotFoundException extends Exception{
    static String message = "Недвижимость не найдена";

    public PropertyNotFoundException() {
        super(message);
    }
}
