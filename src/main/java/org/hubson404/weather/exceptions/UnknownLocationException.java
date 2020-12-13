package org.hubson404.weather.exceptions;

public class UnknownLocationException extends RuntimeException {

    public UnknownLocationException(String message) {
        super("NOT FOUND: " + message);
    }
}
