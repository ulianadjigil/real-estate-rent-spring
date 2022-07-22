package com.example.realestaterent.exception;

public class AddressNotFoundException extends Exception{
    static String message = "Адреса не найдены";

    public AddressNotFoundException() {
        super(message);
    }
}
