package com.group6.ads.controllers.reports.models;

import lombok.Data;

@Data
public class ReportCreateRequest {
    private String fullName;
    private String email;
    private String phone;
    private String content;
    private String reportTypeName;
    private Double latitude;
    private Double longitude;
    private String address;
    private Integer reportFormId;
    private Integer advertiseId;
    private String images;
}
