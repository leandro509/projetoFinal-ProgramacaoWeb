package com.leandro.projeto_petshop_web.handler;

import com.leandro.projeto_petshop_web.exception.BadRequestException;
import com.leandro.projeto_petshop_web.exception.ErrorResponse;
import com.leandro.projeto_petshop_web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
