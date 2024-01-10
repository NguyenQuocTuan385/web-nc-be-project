package com.group6.ads.repositories.database.reports;

import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.report.forms.ReportForm;
import com.group6.ads.repositories.database.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private String content;
    private Integer status;
    private String reportTypeName;
    private String reply;
    private Double latitude;
    private Double longitude;
    private String address;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "report_form_id", nullable = false)
    private ReportForm reportForm;

    @ManyToOne
    @JoinColumn(name = "ads_id", nullable = true)
    private Advertise advertise;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String images;
}
