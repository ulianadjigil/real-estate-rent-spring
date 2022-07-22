package com.example.realestaterent.exception;

public class AddressAlreadyExistException extends Exception{
    static String message = "Такой адрес уже существует";

    public AddressAlreadyExistException() {
        super(message);
    }
}
