package com.group6.ads.repositories.database.properties;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String name;
    private String code;
    @ManyToOne
    @JoinColumn(name = "property_parent_id", nullable = true)
    private Property propertyParent;
}
