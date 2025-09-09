package com.tanmay.library_management.Utility;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tanmay.library_management.DTO.APIResponseWrapper;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponseWrapper<String>> handleException(Exception e) {
        return ResponseEntity.status(500).body(APIResponseWrapper.error("Internal Server Error: ", e.getMessage()));
    }

}
