package com.group6.ads.controllers.contracts.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group6.ads.repositories.database.advertises.Advertise;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ContractRequest {
    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAt;
//    @ManyToOne
//    @JoinColumn(name = "advertise_id", nullable = false)
    private Integer advertiseId;
}
