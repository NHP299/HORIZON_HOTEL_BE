package com.horizon.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.horizon.response.ResponseObject;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseObject<String> handleAllExceptions(Exception ex) {
        return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", ex.getMessage());
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseObject<String> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        return new ResponseObject<>(HttpStatus.NOT_FOUND, "Failed", ex.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseObject<String> handleBadRequestException(HttpClientErrorException.BadRequest ex) {
        return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseObject<?> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseObject<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", ex.getMessage());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseObject<?> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errorMessages = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", errorMessages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseObject<?> handleInvalidFormatException(HttpMessageNotReadableException ex) {
        List<String> errorMessages = new ArrayList<>();

        if (ex.getCause() instanceof JsonMappingException) {
            JsonMappingException jsonEx = (JsonMappingException) ex.getCause();

            jsonEx.getPath().forEach(reference -> {
                String fieldName = reference.getFieldName();
                if (fieldName != null) {
                    errorMessages.add(fieldName + " has incompatible data type");
                } else {
                    errorMessages.add("Unknown field has incompatible data type");
                }
            });
        } else {
            errorMessages.add("Invalid request data");
        }

        return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", errorMessages);
    }


}
