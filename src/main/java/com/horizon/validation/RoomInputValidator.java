package com.horizon.validation;


import org.springframework.stereotype.Component;

@Component
public class RoomInputValidator {
    public void validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input must not be empty.");
        }
    }
}
