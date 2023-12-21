package com.group6.ads.services.reports;

import com.google.gson.Gson;
import com.group6.ads.controllers.report.forms.models.ReportUpdateRequest;
import com.group6.ads.controllers.reports.models.ReportCreateRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import com.group6.ads.repositories.database.report.forms.ReportForm;
import com.group6.ads.repositories.database.report.forms.ReportFormRepository;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.repositories.database.reports.ReportRepository;
import com.group6.ads.util.PageRequestCustom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    private final AdvertiseRepository advertiseRepository;
    private final ReportFormRepository reportFormRepository;
    private final LocationRepository locationRepository;

    @Override
    public Page<Report> findAll(String search, PageRequestCustom pageRequestCustom) {
        return reportRepository.findAll(search, pageRequestCustom.pageRequest());
    }

    @Override
    public Report createReport(ReportCreateRequest reportRequest) {
        Advertise ad = advertiseRepository.findById(reportRequest.getAdvertiseId()).orElseThrow();
        ReportForm rpForm = reportFormRepository.findById(reportRequest.getReportFormId()).orElseThrow();
        Location location = locationRepository.findById(reportRequest.getLocationId()).orElseThrow();

        Report newReport = Report.builder()
                .fullName(reportRequest.getFullName())
                .email(reportRequest.getEmail())
                .phone(reportRequest.getPhone())
                .content(reportRequest.getContent())
                .status(false)
                .reply(null)
                .reportTypeName(reportRequest.getReportTypeName())
                .reportForm(rpForm)
                .advertise(ad)
                .location(location)
                .createdAt(LocalDateTime.now())
                .images(reportRequest.getImages())
                .build();

        return reportRepository.save(newReport);
    }

    @Override
    public Report updateReport(ReportUpdateRequest reportRequest, Integer reportId) {
        Report currentReport = reportRepository.findById(reportId).orElseThrow();

        currentReport.setStatus(reportRequest.getStatus());
        currentReport.setReply(reportRequest.getReply());

        return reportRepository.save(currentReport);
    }

    @Override
    @Transactional
    public void deleteReport(Integer reportId) {
        Report rp = reportRepository
                .findById(reportId)
                .orElseThrow(() -> new NotFoundException("Not found report with id " + reportId));

        reportRepository.delete(rp);
    }
}
