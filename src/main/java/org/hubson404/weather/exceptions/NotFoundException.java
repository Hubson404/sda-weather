package org.hubson404.weather.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("DATA NOT FOUND: " + message);
    }
}
