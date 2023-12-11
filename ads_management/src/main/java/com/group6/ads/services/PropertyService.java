package com.group6.ads.services;

import com.group6.ads.dtos.PropertyCreateDTO;

import java.util.List;
import com.group6.ads.entities.Properties;

public interface PropertyService {
    List<Properties> findAll();
    Properties createProperties(PropertyCreateDTO properties);
}
