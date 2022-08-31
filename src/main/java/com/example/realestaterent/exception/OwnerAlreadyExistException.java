package com.example.realestaterent.exception;

public class OwnerAlreadyExistException extends Exception{
    public OwnerAlreadyExistException() {
        super("Такой владелец уже существует");
    }
}
