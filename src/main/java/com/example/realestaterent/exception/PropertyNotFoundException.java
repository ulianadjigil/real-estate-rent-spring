package com.example.realestaterent.exception;

public class PropertyNotFoundException extends Exception{
    public PropertyNotFoundException() {
        super("Недвижимость не найдена");
    }
}
