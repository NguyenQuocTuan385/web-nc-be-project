package com.group6.ads.repositories.database.advertise.forms;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.locations.Location;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
 * com.group6.ads.repositories.database.advertise.forms
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 12:29 AM
 * Description: ...
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "advertise_forms")
public class AdvertiseForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "adsFromId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Location> locations;
}
