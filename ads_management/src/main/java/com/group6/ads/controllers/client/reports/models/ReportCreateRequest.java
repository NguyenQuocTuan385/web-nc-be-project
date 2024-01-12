package com.group6.ads.controllers.client.reports.models;

import lombok.Data;

@Data
public class ReportCreateRequest {
    private String fullName;
    private String email;
    private String guestEmail;
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
