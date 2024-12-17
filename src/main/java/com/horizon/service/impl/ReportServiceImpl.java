package com.horizon.service.impl;

import com.horizon.repository.ReportRepository;
import com.horizon.response.OverviewResponse;
import com.horizon.response.ReportResponse;
import com.horizon.response.ReportRoomResponse;
import com.horizon.response.ReportUserResponse;
import com.horizon.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public ReportResponse getReports(int month, int year) {
        ReportResponse reportResponse = new ReportResponse();

        reportResponse.setMonthlyRevenueReport(reportRepository.getMonthlyRevenueReport(month, year));
        reportResponse.setTop5BookingDates(reportRepository.getTop5BookingDates(month, year));
        reportResponse.setTop5RoomTypeBookings(reportRepository.getTop5RoomTypeBookings(month, year));
        reportResponse.setTop5RoomBookings(reportRepository.getTop5RoomBookings(month, year));
        reportResponse.setTop5AccountsBySpending(reportRepository.getTop5AccountsBySpending(month, year));
        return reportResponse;
    }

    @Override
    public ReportUserResponse getReportUsers() {

        ReportUserResponse reportUserRespose = new ReportUserResponse();

        reportUserRespose.setTotalAccounts(reportRepository.countTotalAccounts());
        reportUserRespose.setNewAccountsToday(reportRepository.countNewAccountsToday());
        reportUserRespose.setActivatedAccounts(reportRepository.countActivatedAccounts());
        reportUserRespose.setActivatedPercentage(reportRepository.calculateActivatedPercentage());
        return reportUserRespose;
    }

    @Override
    public ReportRoomResponse getReportRooms() {

        ReportRoomResponse reportRoomRespose = new ReportRoomResponse();

        reportRoomRespose.setTotalRooms(reportRepository.countTotalRooms());
        reportRoomRespose.setActiveRooms(reportRepository.countActiveRooms());
        reportRoomRespose.setMaintenanceRooms(reportRepository.countMaintenanceRooms());
        reportRoomRespose.setActivatedPercentage(reportRepository.calculateActiveRoomPercentage());
        return reportRoomRespose;
    }

    @Override
    public OverviewResponse getOverview() {

        OverviewResponse overviewRespose = new OverviewResponse();

        overviewRespose.setTotalRevenueCurrent(reportRepository.getTotalRevenueCurrentMonth());
        overviewRespose.setTotalAccounts(reportRepository.countTotalAccounts());
        overviewRespose.setTotalRooms(reportRepository.countTotalRooms());
        overviewRespose.setTotalBookingsCurrent(reportRepository.countTotalBookingsCurrentMonth());
        overviewRespose.setRoomsByRoomType(reportRepository.countRoomsByRoomType());
        overviewRespose.setMonthlyRevenue(reportRepository.getMonthlyRevenue());
        return overviewRespose;
    }


}
