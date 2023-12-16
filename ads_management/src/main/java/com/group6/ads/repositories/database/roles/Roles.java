package com.group6.ads.repositories.database.roles;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.users.Users;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String code;
    private String description;
//    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private Set<Users> users;
}
