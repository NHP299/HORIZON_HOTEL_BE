package com.horizon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OverviewResponse {
    private Double totalRevenueCurrent;
    private long totalAccounts;
    private long totalRooms;
    private long totalBookingsCurrent;
    private List<Map<String, Object>> roomsByRoomType;
    private List<Map<String, Object>> monthlyRevenue;
}
