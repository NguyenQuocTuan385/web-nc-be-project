package com.group6.ads.controllers.report.forms;

import com.group6.ads.controllers.report.forms.models.ReportFormRequest;
import com.group6.ads.repositories.database.report.forms.ReportForm;
import com.group6.ads.services.report.forms.ReportFormService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * com.group6.ads.controllers.report.forms
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 12:59 PM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/forms")
public class ReportFormController {
    @NonNull
    final ReportFormService reportFormService;

    @GetMapping
    ResponseEntity<List<ReportForm>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(reportFormService.findAll());
    }

    @PostMapping
    ResponseEntity<ReportForm> create(@RequestBody @Valid ReportFormRequest reportFormRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(reportFormService.create(reportFormRequest));
    }

    @PutMapping("{id}")
    ResponseEntity<ReportForm> update(@PathVariable Integer id, @RequestBody @Valid ReportFormRequest reportFormRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(reportFormService.update(id, reportFormRequest));
    }
}
