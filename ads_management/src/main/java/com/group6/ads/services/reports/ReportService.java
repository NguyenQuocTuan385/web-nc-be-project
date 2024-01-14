package com.group6.ads.services.reports;

import com.group6.ads.controllers.admin.report.forms.models.ReportUpdateRequest;
import com.group6.ads.controllers.admin.reports.models.ReportCreateRequest;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

public interface ReportService {
    Page<Report> findAll(String reportTypeName,Integer advertiseId, String email, Integer status, Integer adminPropertyId, String search, PageRequestCustom pageRequestCustom);

    Page<Report> findAll(Integer[] propertyId, Integer[] parentId, String search, PageRequestCustom pageRequestCustom);

    Report createReport(ReportCreateRequest reportRequest);

    Report updateReport(ReportUpdateRequest reportRequest, Integer reportId);

    void deleteReport(Integer reportId);

    Report findReportById(Integer reportId);
}
