package com.group6.ads.services.report.forms;

import com.group6.ads.controllers.admin.report.forms.models.ReportFormRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.report.forms.ReportForm;
import com.group6.ads.repositories.database.report.forms.ReportFormRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * com.group6.ads.services.report.forms
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 1:15 PM
 * Description: ...
 */
@Service
@RequiredArgsConstructor
public class ReportFormServiceImpl implements ReportFormService {
    @NonNull
    final ReportFormRepository reportFormRepository;

    @Override
    public Page<ReportForm> findAll(String search, PageRequestCustom pageRequestCustom) {
        return reportFormRepository.findAll(search, pageRequestCustom.pageRequest());
    }

    @Override
    public ReportForm create(ReportFormRequest reportFormRequest) {
        ReportForm reportForm = ReportForm.builder()
                .name(reportFormRequest.getName())
                .description(reportFormRequest.getDescription())
                .build();
        return reportFormRepository.save(reportForm);
    }

    @Override
    public ReportForm update(Integer id, ReportFormRequest reportFormRequest) {
        ReportForm reportForm = reportFormRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Report form not found"));
        reportForm.setName(reportFormRequest.getName());
        reportForm.setDescription(reportFormRequest.getDescription());
        return reportFormRepository.save(reportForm);
    }

    @Override
    public void delete(Integer id) {
        ReportForm reportForm = reportFormRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Report form not found"));
        reportFormRepository.delete(reportForm);
    }
}
