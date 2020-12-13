package org.hubson404.weather.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super("BAD REQUEST: " + message);
    }
}
