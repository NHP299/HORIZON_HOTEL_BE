package com.horizon.service;

import com.horizon.response.OverviewResponse;
import com.horizon.response.ReportResponse;
import com.horizon.response.ReportRoomResponse;
import com.horizon.response.ReportUserResponse;


public interface ReportService {

    ReportResponse getReports(int month, int year);

    ReportUserResponse getReportUsers();

    ReportRoomResponse getReportRooms();

    OverviewResponse getOverview();
}
