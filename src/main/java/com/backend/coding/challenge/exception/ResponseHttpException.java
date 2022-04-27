package com.backend.coding.challenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ResponseHttpException extends RuntimeException {
    @Getter
    private HttpStatus httpStatus;

    public ResponseHttpException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
