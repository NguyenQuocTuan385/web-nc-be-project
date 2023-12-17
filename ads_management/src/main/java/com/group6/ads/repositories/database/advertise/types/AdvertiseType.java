package com.group6.ads.repositories.database.advertise.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.advertise.edit.AdvertiseEdit;
import com.group6.ads.repositories.database.advertises.Advertise;
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
 * com.group6.ads.repositories.database.advertise.types
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 11:30 PM
 * Description: ...
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "advertise_types")
public class AdvertiseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "adsType", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Advertise> advertises;

    @OneToMany(mappedBy = "adsType", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<AdvertiseEdit> advertiseEdits;
}

