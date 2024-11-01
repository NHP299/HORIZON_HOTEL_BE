package com.horizon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {

    public ResponseEntity<Map<String, Object>> createResponse(String message, Object mediaDto, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        if (mediaDto != null) {
            response.put("media", mediaDto);
        }
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<Map<String, Object>> handleFileUploadError(String errorMessage) {
        return createResponse("File upload error: " + errorMessage, null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Map<String, Object>> handleGeneralError(String errorMessage) {
        return createResponse("An error occurred: " + errorMessage, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Map<String, Object>> handleNotFoundError(String errorMessage) {
        return createResponse(errorMessage, null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> handleDeletionError(String errorMessage) {
        return createResponse("Failed to delete file: " + errorMessage, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
