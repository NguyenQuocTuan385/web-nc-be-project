package com.group6.ads.services.reports;

import com.group6.ads.controllers.reports.models.ReportCreateRequest;
import com.group6.ads.repositories.database.reports.Report;

import java.util.List;

public interface ReportService {
    List<Report> findAll();

    Report createReport(ReportCreateRequest reportRequest);
}
