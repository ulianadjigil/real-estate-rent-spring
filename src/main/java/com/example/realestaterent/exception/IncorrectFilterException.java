package com.example.realestaterent.exception;

public class IncorrectFilterException extends Exception{
    public IncorrectFilterException() {
        super("Введены неправильные параметры фильтрации");
    }
}
