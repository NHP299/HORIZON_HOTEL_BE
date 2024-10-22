package com.horizon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Lookup;

@Getter
@AllArgsConstructor
public enum RoomStatus {
    AVAILABLE(0, "Available"),
    OCCUPIED(1, "Occupied"),
    RESERVED(2, "Reserved"),
    MAINTENANCE(3, "Maintenance"); // Thêm trạng thái bảo trì

    private final int code;
    private final String description;

    public static RoomStatus fromCode(int code) {
        for (RoomStatus status : RoomStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }

    public static int fromDescription(String description) {
        for (RoomStatus status : RoomStatus.values()) {
            if (status.getDescription().equalsIgnoreCase(description)) {
                return status.getCode();
            }
        }
        throw new IllegalArgumentException("Unknown status description: " + description);
    }
}
