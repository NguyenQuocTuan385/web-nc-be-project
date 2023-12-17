package com.group6.ads.controllers.reports;

import com.group6.ads.controllers.reports.models.ReportCreateRequest;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.services.reports.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("")
    ResponseEntity<List<Report>> getAllReports(){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.findAll());
    }

    @PostMapping("")
    ResponseEntity<Report> createReport(@RequestBody ReportCreateRequest reportCreateRequest){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.createReport(reportCreateRequest));
    }
}
