package com.group6.ads.controllers.admin.reports;

import com.group6.ads.controllers.admin.report.forms.models.ReportUpdateRequest;
import com.group6.ads.controllers.admin.reports.models.ReportCreateRequest;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.services.reports.ReportService;
import com.group6.ads.util.PageRequestCustom;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}")
@CrossOrigin("*")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("reports")
    ResponseEntity<?> getAllReports(
        @RequestParam(required = false, value = "reportTypeName")
        String reportTypeName,
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
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(reportService.findAll(reportTypeName,locationId, email, search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("properties/reports")
    ResponseEntity<?> getReportsWithPropertyIds(
            @RequestParam(required = false, value = "propertyId[]", defaultValue = "")
            Integer[] propertyId,
            @RequestParam(required = false, value = "parentId[]", defaultValue = "")
            Integer[] parentId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);

            if (parentId.length == 0)
                parentId = null;
            if (propertyId.length == 0)
                propertyId = null;
            return ResponseEntity.status(HttpStatus.OK)
                    .body(reportService.findAll(propertyId, parentId, search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("reports")
    ResponseEntity<?> createReport(@RequestBody ReportCreateRequest reportCreateRequest){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reportService.createReport(reportCreateRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("reports/{id}")
    ResponseEntity<?> updateReport(@PathVariable int id, @RequestBody ReportUpdateRequest reportUpdateRequest){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reportService.updateReport(reportUpdateRequest, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("reports/{id}")
    ResponseEntity<?> deleteReport(@PathVariable int id){
        try {
            reportService.deleteReport(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete report with id " + id + " successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("reports/{id}")
    ResponseEntity<?> findReportById(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reportService.findReportById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
