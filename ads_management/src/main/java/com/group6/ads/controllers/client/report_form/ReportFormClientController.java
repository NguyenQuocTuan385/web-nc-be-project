package com.group6.ads.controllers.client.report_form;

import com.group6.ads.services.report.forms.ReportFormService;
import com.group6.ads.util.PageRequestCustom;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/report-forms-client")
@CrossOrigin("*")
public class ReportFormClientController {
    @NonNull
    final ReportFormService reportFormService;

    @GetMapping
    ResponseEntity<?> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(reportFormService.findAll(search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
