package com.kriNad.backend.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException() {
        super("The request could not be found");
    }
}
