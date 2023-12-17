package com.group6.ads.controllers.reports.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.images.Image;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.report.forms.ReportForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ReportCreateRequest {
    private String fullName;
    private String email;
    private String phone;
    private String content;
    private String reportTypeName;

    private Integer reportFormId;
    private Integer advertiseId;
    private Integer locationId;
    private String[] imageUrls;
}
