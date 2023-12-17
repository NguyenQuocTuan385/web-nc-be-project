package com.group6.ads.repositories.database.locations;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.images.Image;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.reports.Report;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * com.group6.ads.repositories.database.localtions
 * Create by Dang Ngoc Tien
 * Date 12/16/2023 - 11:18 PM
 * Description: ...
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean planning;
    private Double latitude;
    private Double longitude;
    private String address;
    private Boolean statusEdit;
    private Integer locationEditId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "ads_form_id", nullable = false)
    private AdvertiseForm adsFromId;

    @ManyToOne
    @JoinColumn(name = "location_type_id", nullable = false)
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Advertise> advertises;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Report> reports;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Image> images;
}