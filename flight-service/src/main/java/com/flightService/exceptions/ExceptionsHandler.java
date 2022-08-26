package com.flightService.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(value = {TicketException.class})
    public ResponseEntity<?> ticketExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FlightException.class})
    public ResponseEntity<?> flightExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
