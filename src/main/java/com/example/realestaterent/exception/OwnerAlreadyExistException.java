package com.example.realestaterent.exception;

public class OwnerAlreadyExistException extends Exception{
    static String message = "Такой владелец уже существует";

    public OwnerAlreadyExistException() {
        super(message);
    }
}
