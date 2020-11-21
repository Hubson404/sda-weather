package org.hubson404.weather.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super("INVALID DATA:" + message);
    }
}
