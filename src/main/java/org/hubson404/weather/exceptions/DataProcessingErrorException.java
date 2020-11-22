package org.hubson404.weather.exceptions;

public class DataProcessingErrorException extends RuntimeException {

    public DataProcessingErrorException(String message) {
        super("DATA PROCESSING ERROR: " + message);
    }
}
