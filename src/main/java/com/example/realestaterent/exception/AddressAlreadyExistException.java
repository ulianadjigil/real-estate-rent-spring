package com.example.realestaterent.exception;

public class AddressAlreadyExistException extends Exception{
    public AddressAlreadyExistException() {
        super("Такой адрес уже существует");
    }
}
