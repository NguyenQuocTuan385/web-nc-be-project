package com.group6.ads.controllers.contracts.models;

import jakarta.validation.constraints.NotNull;

import lombok.Data;
@Data
public class ContractUpdateStatusRequest {
    @NotNull
    private Integer status;
}
