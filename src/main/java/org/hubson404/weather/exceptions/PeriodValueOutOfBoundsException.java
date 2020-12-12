package org.hubson404.weather.exceptions;

public class PeriodValueOutOfBoundsException extends RuntimeException {
    public PeriodValueOutOfBoundsException(String message) {
        super("VALUE OUT OF BOUNDS: " +message);
    }
}
