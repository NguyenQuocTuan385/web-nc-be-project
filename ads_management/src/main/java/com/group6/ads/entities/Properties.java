package com.group6.ads.entities;

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
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer property_parent_id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "properties", cascade = CascadeType.ALL)
    private Set<Users> users;

    @OneToMany(mappedBy = "parentProperty", cascade = CascadeType.ALL)
    private Set<Properties> childProperties;

    @ManyToOne
    @JoinColumn(name = "property_parent_id", insertable = false, updatable = false)
    private Properties parentProperty;



}
