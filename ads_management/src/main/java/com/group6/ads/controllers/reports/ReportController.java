package com.group6.ads.controllers.reports;

import com.group6.ads.controllers.report.forms.models.ReportUpdateRequest;
import com.group6.ads.controllers.reports.models.ReportCreateRequest;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.services.reports.ReportService;
import com.group6.ads.util.PageRequestCustom;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/reports")
@CrossOrigin("*")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("")
    ResponseEntity<Page<Report>> getAllReports(
        @RequestParam(required = false, value = "locationId")
        Integer locationId,
        @RequestParam (required = false, value = "email")
        String email,
        @RequestParam(required = false, value = "search", defaultValue = "")
        String search,
        @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
        Integer currentPage,
        @RequestParam(required = false, value = "pageSize", defaultValue = "10")
        Integer pageSize
    ){
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(reportService.findAll(locationId, email, search, pageRequestCustom));
    }

    @PostMapping("")
    ResponseEntity<Report> createReport(@RequestBody ReportCreateRequest reportCreateRequest){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.createReport(reportCreateRequest));
    }

    @PutMapping("{id}")
    ResponseEntity<Report> updateReport(@PathVariable int id, @RequestBody ReportUpdateRequest reportUpdateRequest){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.updateReport(reportUpdateRequest, id));
    }

    @DeleteMapping("{id}")
    ResponseEntity<String> deleteReport(@PathVariable int id){
        reportService.deleteReport(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete report with id " + id + " successful");
    }
}
