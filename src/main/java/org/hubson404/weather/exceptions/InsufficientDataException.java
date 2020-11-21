package org.hubson404.weather.exceptions;

public class InsufficientDataException extends RuntimeException {

    public InsufficientDataException(String message) {
        super(message);
    }
}
