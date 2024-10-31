package com.gymbro.userservice.exceptions;

public class BadUserIdentifer extends RuntimeException {
    public BadUserIdentifer(String identifierType, String identifier) {
        super(String.format("Could not identify user using %s: %s", identifierType, identifier));
    }

}
