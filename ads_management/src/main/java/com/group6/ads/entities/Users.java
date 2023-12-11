package com.group6.ads.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String password;
    private LocalDate birthday;
    private String avatar;
    private String phone;


    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Roles roles;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Properties properties;
}
