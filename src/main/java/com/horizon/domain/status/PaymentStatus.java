package com.horizon.domain.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    PENDING(1, "Pending"),
    SUCCESS(2, "Success"),
    FAILED(3, "Failed");

    private final int code;
    private final String description;

    public static PaymentStatus fromCode(int code) {
        for (PaymentStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }

    public static PaymentStatus fromDescription(String description) {
        for (PaymentStatus status : values()) {
            if (status.description.equals(description)) {
                return status;
            }
        }
        return null;
    }

}
