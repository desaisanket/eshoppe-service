package com.eshoppe.shopservice.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public void handleConstraintViolation(MethodArgumentNotValidException ex) {
         throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Invalid input data passed.", ex);
    }

    @ExceptionHandler({ SQLException.class })
    public void handleSQLException(SQLException ex) {
        throw new ResponseStatusException( HttpStatus.INTERNAL_SERVER_ERROR, "Technical Error has occurred", ex);
    }

    @ExceptionHandler({ NullPointerException.class })
    public void handleNullPointerException(NullPointerException ex) {
        throw new ResponseStatusException( HttpStatus.INTERNAL_SERVER_ERROR, "Technical Error has occurred", ex);
    }
}
