package com.group6.ads.controllers.contracts.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContractRequest {
    @NotBlank
    private String companyName;

    @NotBlank
    private String companyEmail;

    @NotBlank
    private String companyPhone;

    @NotBlank
    private String companyAddress;

    @NotBlank
    private String image;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAt;
//    @ManyToOne
//    @JoinColumn(name = "advertise_id", nullable = false)

    @NotNull(message = "Advertise Id must not be null")
    private Integer advertiseId;
}
