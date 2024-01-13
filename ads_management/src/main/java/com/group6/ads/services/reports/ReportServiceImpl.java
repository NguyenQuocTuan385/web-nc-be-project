package com.group6.ads.services.reports;

import com.group6.ads.controllers.admin.report.forms.models.ReportUpdateRequest;
import com.group6.ads.controllers.admin.reports.models.ReportCreateRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.locations.LocationRepository;
import com.group6.ads.repositories.database.report.forms.ReportForm;
import com.group6.ads.repositories.database.report.forms.ReportFormRepository;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.repositories.database.reports.ReportRepository;
import com.group6.ads.util.PageRequestCustom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    private final AdvertiseRepository advertiseRepository;
    private final ReportFormRepository reportFormRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Page<Report> findAll(String reportTypeName,Integer locationId, String email, String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all report");
            if (email != null) {
                if (locationId != null)
                    return reportRepository.findAllByEmailAndLocation(locationId, email, search, pageRequestCustom.pageRequest());
                return reportRepository.findAllByEmailAndReportTypeName(email, reportTypeName, search, pageRequestCustom.pageRequest());
            }
            return reportRepository.findAll(reportTypeName,search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report");
        }
    }

    @Override
    public Page<Report> findAll(Integer[] propertyId, Integer[] parentId, String search, PageRequestCustom pageRequestCustom) {
        try {
            logger.info("Find all report");
            return reportRepository.findAll(propertyId, parentId, search, pageRequestCustom.pageRequest());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report");
        }
    }

    @Override
    public Report createReport(ReportCreateRequest reportRequest) {
        try {
            logger.info("Create new report");
            Advertise ad = advertiseRepository.findById(reportRequest.getAdvertiseId()).orElseThrow();
            ReportForm rpForm = reportFormRepository.findById(reportRequest.getReportFormId()).orElseThrow();

            Report newReport = Report.builder()
                    .fullName(reportRequest.getFullName())
                    .email(reportRequest.getEmail())
                    .phone(reportRequest.getPhone())
                    .content(reportRequest.getContent())
                    .reply(null)
                    .status(1)
                    .guestEmail(reportRequest.getGuestEmail())
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
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report");
        }
    }

    @Override
    public Report updateReport(ReportUpdateRequest reportRequest, Integer reportId) {
        try {
            logger.info("Update report");
            Report currentReport = reportRepository.findById(reportId).orElseThrow();

            currentReport.setStatus(reportRequest.getStatus());
            currentReport.setReply(reportRequest.getReply());

            return reportRepository.save(currentReport);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report");
        }
    }

    @Override
    @Transactional
    public void deleteReport(Integer reportId) {
        try {
            logger.info("Delete report");
            Report rp = reportRepository
                    .findById(reportId)
                    .orElseThrow(() -> new NotFoundException("Not found report with id " + reportId));

            reportRepository.delete(rp);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report");
        }
    }

    @Override
    public Report findReportById(Integer reportId) {
        try {
            logger.info("Find report by id");
            return reportRepository.findById(reportId).orElseThrow(() -> new NotFoundException("Not found report with id " + reportId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new NotFoundException("Not found report");
        }
    }
}
