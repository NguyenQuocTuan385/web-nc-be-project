package com.group6.ads.repositories.database.advertises;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.contracts.Contract;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer licensing;
    private float height;
    private float width;
    private String image;

//    @ManyToOne
//    @JoinColumn(name = "location_id", nullable = false)
//    private Location locationId;
//
//    @ManyToOne
//    @JoinColumn(name = "ads_type_id", nullable = false)
//    private AdvertiseType adsTypeId;

    private Integer locationId;
    private Integer adsTypeId;

//    @OneToMany(mappedBy = "advertises", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private Integer contracts;
}
