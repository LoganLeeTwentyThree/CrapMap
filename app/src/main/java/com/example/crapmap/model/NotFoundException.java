package com.example.crapmap.model;

public class NotFoundException extends Exception
{
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
