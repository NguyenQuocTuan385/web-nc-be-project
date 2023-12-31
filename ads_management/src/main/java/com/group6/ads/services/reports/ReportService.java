package com.group6.ads.services.reports;

import com.group6.ads.controllers.report.forms.models.ReportUpdateRequest;
import com.group6.ads.controllers.reports.models.ReportCreateRequest;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReportService {
    Page<Report> findAll(String reportTypeName,Integer advertiseId, String email, String search, PageRequestCustom pageRequestCustom);

    Page<Report> findAll(Integer[] propertyId, Integer[] parentId, String search, PageRequestCustom pageRequestCustom);

    Report createReport(ReportCreateRequest reportRequest);

    Report updateReport(ReportUpdateRequest reportRequest, Integer reportId);

    void deleteReport(Integer reportId);

    Report findReportById(Integer reportId);
}
