package com.group6.ads.controllers.admin.report.forms;

import com.group6.ads.controllers.admin.report.forms.models.ReportFormRequest;
import com.group6.ads.repositories.database.report.forms.ReportForm;
import com.group6.ads.services.report.forms.ReportFormService;
import com.group6.ads.util.PageRequestCustom;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * com.group6.ads.controllers.report.forms
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 12:59 PM
 * Description: ...
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/report-forms")
@CrossOrigin("*")
public class ReportFormController {
    @NonNull
    final ReportFormService reportFormService;

    @GetMapping
    ResponseEntity<Page<ReportForm>> findAll(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(reportFormService.findAll(search, pageRequestCustom));
    }

    @PostMapping
    ResponseEntity<ReportForm> create(@RequestBody @Valid ReportFormRequest reportFormRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(reportFormService.create(reportFormRequest));
    }

    @PutMapping("{id}")
    ResponseEntity<ReportForm> update(@PathVariable Integer id, @RequestBody @Valid ReportFormRequest reportFormRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(reportFormService.update(id, reportFormRequest));
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        reportFormService.delete(id);
        return ResponseEntity.ok().build();
    }
}
