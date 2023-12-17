package com.group6.ads.services.reports;

import com.group6.ads.controllers.report.forms.models.ReportUpdateRequest;
import com.group6.ads.controllers.reports.models.ReportCreateRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.images.Image;
import com.group6.ads.repositories.database.images.ImageRepository;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.locations.LocationRepository;
import com.group6.ads.repositories.database.report.forms.ReportForm;
import com.group6.ads.repositories.database.report.forms.ReportFormRepository;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.repositories.database.reports.ReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    private final AdvertiseRepository advertiseRepository;
    private final ReportFormRepository reportFormRepository;
    private final ImageRepository imageRepository;
    private final LocationRepository locationRepository;

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report createReport(ReportCreateRequest reportRequest) {
        Advertise ad = advertiseRepository.findById(reportRequest.getAdvertiseId()).orElseThrow();
        ReportForm rpForm = reportFormRepository.findById(reportRequest.getReportFormId()).orElseThrow();
        Location location = locationRepository.findById(reportRequest.getLocationId()).orElseThrow();
        String[] imageUrlsList = reportRequest.getImageUrls();

        Report newReport = Report.builder()
                .fullName(reportRequest.getFullName())
                .email(reportRequest.getEmail())
                .phone(reportRequest.getPhone())
                .content(reportRequest.getContent())
                .status(0)
                .reply(null)
                .reportTypeName(reportRequest.getReportTypeName())
                .reportForm(rpForm)
                .advertise(ad)
                .location(location)
                .createdAt(LocalDateTime.now())
                .build();

        Report tmp = reportRepository.save(newReport);

        // save img url to images table
        for (String image : imageUrlsList) {
            Image img = Image.builder()
                    .imgUrl(image)
                    .location(location)
                    .locationEditId(location.getLocationEditId())
                    .report(tmp)
                    .build();

            imageRepository.save(img);
        }

        return tmp;
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

        // delete image of this report
        try{
            imageRepository.deleteByReportId(reportId);
        }
        catch (Exception e) {
            throw new NotFoundException("Not found Images with this id");
        }

        reportRepository.delete(rp);
    }
}
