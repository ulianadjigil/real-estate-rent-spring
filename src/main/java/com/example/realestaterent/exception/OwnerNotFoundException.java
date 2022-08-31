package com.example.realestaterent.exception;

public class OwnerNotFoundException extends Exception{
    public OwnerNotFoundException() {
        super("Владельцы не найдены");
    }
}
