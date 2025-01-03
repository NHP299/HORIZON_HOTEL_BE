package com.horizon.controller.admin;

import com.horizon.response.OverviewResponse;
import com.horizon.response.ReportResponse;
import com.horizon.response.ReportRoomResponse;
import com.horizon.response.ReportUserResponse;
import com.horizon.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/admin/reports")
public class ReportController {
    private final ReportService reportService;


//    @GetMapping("/generateReport")
//    public ReportResponse generateReport(@RequestParam int month, @RequestParam int year) {
//        return reportService.getReports(month, year);
//    }
//
//    @GetMapping("/reportUser")
//    public ReportUserResponse reportUser() {
//        return reportService.getReportUsers();
//    }
//
//    @GetMapping("/reportRoom")
//    public ReportRoomResponse reportRoom() {
//        return reportService.getReportRooms();
//    }
//
//    @GetMapping("/overview")
//    public OverviewResponse overview() {
//        return reportService.getOverview();
//    }
}
