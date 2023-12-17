package com.group6.ads.repositories.database.advertises;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.reports.Report;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "advertises")
public class Advertise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean licensing;
    private Double height;
    private Double width;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "ads_type_id", nullable = false)
    private AdvertiseType adsTypeId;

    private Boolean statusEdit;
    private Integer statusEditId;


    @OneToMany(mappedBy = "advertise", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Contract> contracts;

    @OneToMany(mappedBy = "advertise", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Report> reports;
}
