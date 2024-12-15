package com.horizon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRoomResponse {
    private long totalRooms;
    private long activeRooms;
    private long maintenanceRooms;
    private double activatedPercentage;
}
