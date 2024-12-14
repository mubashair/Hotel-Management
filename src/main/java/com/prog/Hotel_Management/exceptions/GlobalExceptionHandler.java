package com.prog.Hotel_Management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle specific exception: HotelNotFoundException
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleHotelNotFoundException(HotelNotFoundException ex) {
        // Create an ErrorDetails object with a custom error message
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        // Return a ResponseEntity with the error details and HTTP status
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Handle general exceptions (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex) {
        // Create an ErrorDetails object for generic exceptions
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        // Return a ResponseEntity with the error details and HTTP status
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(BookingNotFoundException ex) {
        // Create an ErrorDetails object for generic exceptions
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        // Return a ResponseEntity with the error details and HTTP status
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
