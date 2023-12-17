package com.group6.ads.repositories.database.location.edit;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.images.Image;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.reports.Report;
import com.group6.ads.repositories.database.users.User;
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
 * com.group6.ads.repositories.database.locations
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
@Table(name = "locations_edit")
public class LocationEdit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean planning;
    private Double latitude;
    private Double longitude;
    private String address;
    private String content;

    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "ads_form_id", nullable = false)
    @JsonIgnore
    private AdvertiseForm adsForm;

    @ManyToOne
    @JoinColumn(name = "location_type_id", nullable = false)
    @JsonIgnore
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    @JsonIgnore
    private Property property;

    @OneToMany(mappedBy = "locationEdit", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private Set<Image> images;
}