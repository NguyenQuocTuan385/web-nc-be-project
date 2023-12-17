package com.group6.ads.services.reports;

import com.group6.ads.controllers.reports.models.ReportCreateRequest;
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
import com.group6.ads.repositories.database.roles.RoleRepository;
import lombok.RequiredArgsConstructor;
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

        // TODO: add image urls to image table after add new report
//        LocationEd
//        for (String image : imageUrlsList) {
//            Image img = Image.builder()
//                    .imgUrl(image)
//                    .location(location)
//                    .locationEditId()
//                    .build();
//        }

        return reportRepository.save(newReport);
    }
}
