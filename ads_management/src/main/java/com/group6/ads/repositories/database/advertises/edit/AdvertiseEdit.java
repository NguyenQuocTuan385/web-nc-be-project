package com.group6.ads.repositories.database.advertises.edit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.advertise.types.AdvertiseType;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "advertises_edit")
public class AdvertiseEdit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer licensing;
    private float height;
    private float width;
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "ads_type_id", nullable = false)
    private AdvertiseType adsType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "advertiseEdit", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private Set<Advertise> advertises;
}
