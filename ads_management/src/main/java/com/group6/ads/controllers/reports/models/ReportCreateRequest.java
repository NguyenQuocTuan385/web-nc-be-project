package com.group6.ads.controllers.reports.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportCreateRequest {
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private String content;
    private Integer status;

}
