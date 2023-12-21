package com.group6.ads.repositories.database.contracts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group6.ads.repositories.database.advertises.Advertise;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAt;

    private Integer status;
    private String images;

    @ManyToOne
    @JoinColumn(name = "ads_id", nullable = false)
    private Advertise advertise;
}
