package com.group6.ads.repositories.database.contracts;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endAt;

    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;
    private Integer status;
    private String image;

    @ManyToOne
    @JoinColumn(name = "advertise_id", nullable = false)
    @JsonBackReference
    private Advertise advertises;
}
