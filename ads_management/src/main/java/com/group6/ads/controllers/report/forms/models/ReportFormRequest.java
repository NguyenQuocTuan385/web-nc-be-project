package com.group6.ads.controllers.report.forms.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * com.group6.ads.controllers.report.forms.models
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 1:19 PM
 * Description: ...
 */
@Data
public class ReportFormRequest {
    @NotBlank(message = "name is not blank")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50")
    private String name;
    @NotBlank(message = "description is not blank")
    @Size(min = 1, max = 256, message = "description must be between 1 and 50")
    private String description;
}
