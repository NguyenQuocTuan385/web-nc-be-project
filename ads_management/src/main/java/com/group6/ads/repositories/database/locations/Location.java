package com.group6.ads.repositories.database.locations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.group6.ads.repositories.database.advertise.forms.AdvertiseForm;
import com.group6.ads.repositories.database.locations.edit.LocationEdit;
import com.group6.ads.repositories.database.location.types.LocationType;
import com.group6.ads.repositories.database.properties.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String images;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "ads_form_id", nullable = false)
    private AdvertiseForm adsForm;

    @ManyToOne
    @JoinColumn(name = "location_type_id", nullable = false)
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @OneToOne
    @JoinColumn(name = "location_edit_id")
    private LocationEdit locationEdit;
}
