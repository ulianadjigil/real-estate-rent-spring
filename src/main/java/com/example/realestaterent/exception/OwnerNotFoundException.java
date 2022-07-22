package com.example.realestaterent.exception;

public class OwnerNotFoundException extends Exception{
    static String message = "Владельцы не найдены";

    public OwnerNotFoundException() {
        super(message);
    }
}
