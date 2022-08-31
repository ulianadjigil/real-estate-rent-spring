package com.example.realestaterent.exception;

public class RequiredParamNotEnteredException extends Exception{
    public RequiredParamNotEnteredException() {
        super("Введены не все обязательные параметры обьекта");
    }
}
