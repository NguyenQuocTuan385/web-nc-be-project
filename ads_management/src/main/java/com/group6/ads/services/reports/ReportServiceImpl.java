package com.group6.ads.services.reports;

import com.google.gson.Gson;
import com.group6.ads.controllers.report.forms.models.ReportUpdateRequest;
import com.group6.ads.controllers.reports.models.ReportCreateRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.contracts.Contract;
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

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    private final AdvertiseRepository advertiseRepository;
    private final ReportFormRepository reportFormRepository;
    private final LocationRepository locationRepository;

    @Override
    public Page<Report> findAll(String reportTypeName,Integer locationId, String email, String search, PageRequestCustom pageRequestCustom) {
        if (email != null && locationId != null) {
            return reportRepository.findAllByEmailAndLocation(locationId, email, search, pageRequestCustom.pageRequest());
        }
        return reportRepository.findAll(reportTypeName,search, pageRequestCustom.pageRequest());
    }

    @Override
    public Page<Report> findAll(Integer[] propertyId, Integer[] parentId, String search, PageRequestCustom pageRequestCustom) {
        return reportRepository.findAll(propertyId, parentId, search, pageRequestCustom.pageRequest());
    }

    @Override
    public Report createReport(ReportCreateRequest reportRequest) {
        Advertise ad = advertiseRepository.findById(reportRequest.getAdvertiseId()).orElseThrow();
        ReportForm rpForm = reportFormRepository.findById(reportRequest.getReportFormId()).orElseThrow();

        Report newReport = Report.builder()
                .fullName(reportRequest.getFullName())
                .email(reportRequest.getEmail())
                .phone(reportRequest.getPhone())
                .content(reportRequest.getContent())
                .reply(null)
                .reportTypeName(reportRequest.getReportTypeName())
                .address(reportRequest.getAddress())
                .latitude(reportRequest.getLatitude())
                .longitude(reportRequest.getLongitude())
                .reportForm(rpForm)
                .advertise(ad)
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

    @Override
    public Report findReportById(Integer reportId) {
        return reportRepository.findById(reportId).orElseThrow(() -> new NotFoundException("Not found report with id " + reportId));
    }
}
