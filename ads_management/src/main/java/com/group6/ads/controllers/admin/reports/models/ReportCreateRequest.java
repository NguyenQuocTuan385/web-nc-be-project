package com.group6.ads.controllers.admin.reports.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportCreateRequest {
    private String fullName;
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid guest email format")
    private String guestEmail;
    private String phone;
    private String content;
    @NotBlank(message = "report type name must be not blank")
    private String reportTypeName;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    private String address;
    @NotNull
    private Integer reportFormId;
    private Integer propertyId;
    private Integer advertiseId;
    private String images;
}
