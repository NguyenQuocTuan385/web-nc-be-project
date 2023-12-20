package com.group6.ads.controllers.reports.models;

import lombok.Data;

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
    private String images;
}
