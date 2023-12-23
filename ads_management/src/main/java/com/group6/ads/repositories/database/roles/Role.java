package com.group6.ads.repositories.database.roles;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String code;
    private String description;
}
