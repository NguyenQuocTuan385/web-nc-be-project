package com.group6.ads.dtos;

import com.group6.ads.entities.Properties;
import com.group6.ads.entities.Roles;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateDTO {
    @NotBlank(message = "Name must be not blank")
    @Size(min = 1, max = 100, message = "Namme must be between 1 and 50")
    private String name;
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password must be not blank")
    private String password;

    @NotNull(message = "Birthday must be not null")
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
