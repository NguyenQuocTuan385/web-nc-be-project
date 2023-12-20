package com.group6.ads.repositories.database.advertises;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.advertises.edit.AdvertiseEdit;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.reports.Report;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "advertises")
public class Advertise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean licensing;
    private Double height;
    private Double width;
    private Boolean statusEdit;
    private String images;
    private Integer pillarQuantity;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference
    private Location location;

    @ManyToOne
    @JoinColumn(name = "ads_type_id", nullable = false)
    private AdvertiseType adsType;

    @OneToOne
    @JoinColumn(name = "advertise_edit_id", nullable = false)
    private AdvertiseEdit advertiseEdit;

    @OneToMany(mappedBy = "advertise", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Contract> contracts;

    @OneToMany(mappedBy = "advertise", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Report> reports;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
