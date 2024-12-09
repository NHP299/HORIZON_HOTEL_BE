package com.horizon.domain.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingStatus {
    PENDING(1, "Pending"),
    CONFIRMED(2, "Confirmed"),
    CANCELLED(3, "Cancelled"),
    COMPLETED(4, "Completed");

    private final int code;
    private final String description;

    public static BookingStatus fromCode(int code) {
        for (BookingStatus status : BookingStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid BookingStatus code: " + code);
    }

    public static int fromDescription(String description) {
        for (BookingStatus status : BookingStatus.values()) {
            if (status.getDescription().equalsIgnoreCase(description)) {
                return status.getCode();
            }
        }
        throw new IllegalArgumentException("Unknown BookingStatus description: " + description);
    }
}
