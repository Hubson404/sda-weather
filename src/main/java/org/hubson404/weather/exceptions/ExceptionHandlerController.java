package org.hubson404.weather.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {


    @ExceptionHandler(PeriodValueOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void PeriodValueOutOfBoundsHandler(PeriodValueOutOfBoundsException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(UnknownLocationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void unknownLocationHandler(UnknownLocationException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(InsufficientDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void insufficientDataHandler(InsufficientDataException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void invalidDataHandler(InvalidDataException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFoundHandler(NotFoundException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badRequestHandler(BadRequestException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(DataProcessingErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void dataProcessingErrorHandler(DataProcessingErrorException exception) {
        log.error(exception.getMessage());
    }

}
