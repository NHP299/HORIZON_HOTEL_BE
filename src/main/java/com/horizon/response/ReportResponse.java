package com.horizon.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private List<Map<String, Object>> monthlyRevenueReport;
    private List<Map<String, Object>> top5BookingDates;
    private List<Map<String, Object>> top5RoomTypeBookings;
    private List<Map<String, Object>> top5RoomBookings;
    private List<Map<String, Object>> top5AccountsBySpending;
}
