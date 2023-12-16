package com.group6.ads.repositories.database.users;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.repositories.database.roles.Roles;
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

//
//    @ManyToOne
//    @JoinColumn(name = "role_id", nullable = false)
    private Integer roles;
//
//    @ManyToOne
//    @JoinColumn(name = "property_id", nullable = false)
    private Integer property;
}
