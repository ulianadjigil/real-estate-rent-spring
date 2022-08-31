package com.example.realestaterent.exception;

public class AddressNotFoundException extends Exception{
    public AddressNotFoundException() {
        super("Адреса не найдены");
    }
}
