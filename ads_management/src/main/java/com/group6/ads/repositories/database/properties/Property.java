package com.group6.ads.repositories.database.properties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.locations.Location;
import com.group6.ads.repositories.database.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer propertyParentId;
    private String name;
    private String code;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Location> locations;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<User> users;
}
