package com.group6.ads.controllers.client.reports;

import com.group6.ads.controllers.reports.models.ReportCreateRequest;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.services.reports.ReportService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Report Client", description = "Report Client API")
public class ReportClientController {
    private final ReportService reportService;

    @GetMapping("reports-client")
    ResponseEntity<Page<Report>> getAllReports(
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
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(reportService.findAll(reportTypeName,locationId, email, search, pageRequestCustom));
    }

    @PostMapping("reports-client")
    ResponseEntity<Report> createReport(@RequestBody ReportCreateRequest reportCreateRequest){
        return ResponseEntity.status(HttpStatus.OK).body(reportService.createReport(reportCreateRequest));
    }
}
