package com.group6.ads.controllers.email.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailSenderRequest {
    @NotBlank(message = "send email to is not blank")
    @Size(min = 1, max = 256, message = "send email to must be between 1 and 256")
    private String toEmail;

    private String subject;
    private String body;
}
