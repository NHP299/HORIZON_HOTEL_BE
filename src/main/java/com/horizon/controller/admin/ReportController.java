package com.horizon.controller.admin;

import com.horizon.response.*;
import com.horizon.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController("AdminReportController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/generateReport")
    public ResponseObject<?> generateReport(@RequestParam int month, @RequestParam int year) {
        return new ResponseObject<>(HttpStatus.OK, "Success", reportService.getReports(month, year));
    }

    @GetMapping("/reportUser")
    public ResponseObject<?> reportUser() {
        return new ResponseObject<>(HttpStatus.OK, "Success", reportService.getReportUsers());
    }

    @GetMapping("/reportRoom")
    public ResponseObject<?> reportRoom() {
        return new ResponseObject<>(HttpStatus.OK, "Success", reportService.getReportRooms());
    }

    @GetMapping("/overview")
    public ResponseObject<?> overview() {
        return new ResponseObject<>(HttpStatus.OK, "Success", reportService.getOverview());
    }
}
