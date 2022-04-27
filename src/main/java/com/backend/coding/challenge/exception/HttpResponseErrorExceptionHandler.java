package com.backend.coding.challenge.exception;

import com.backend.coding.challenge.api.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpResponseErrorExceptionHandler {

    @ExceptionHandler(ResponseHttpException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ResponseHttpException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(e.getHttpStatus(), e.getMessage())
                );
    }
}
