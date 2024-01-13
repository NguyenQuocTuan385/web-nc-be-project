package com.group6.ads.services.report.forms;

import com.group6.ads.controllers.admin.report.forms.models.ReportFormRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.report.forms.ReportForm;
import com.group6.ads.repositories.database.report.forms.ReportFormRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Page<ReportForm> findAll(String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all report form");
            return reportFormRepository.findAll(search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report form");
        }
    }

    @Override
    public ReportForm create(ReportFormRequest reportFormRequest) {
        try {
            logger.info("Create new report form");
            ReportForm reportForm = ReportForm.builder()
                    .name(reportFormRequest.getName())
                    .description(reportFormRequest.getDescription())
                    .build();
            return reportFormRepository.save(reportForm);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report form");
        }
    }

    @Override
    public ReportForm update(Integer id, ReportFormRequest reportFormRequest) {
        try {
            logger.info("Update report form");
            ReportForm reportForm = reportFormRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Report form not found"));
            reportForm.setName(reportFormRequest.getName());
            reportForm.setDescription(reportFormRequest.getDescription());
            return reportFormRepository.save(reportForm);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report form");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            logger.info("Delete report form");
            ReportForm reportForm = reportFormRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Report form not found"));
            reportFormRepository.delete(reportForm);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report form");
        }
    }
}
