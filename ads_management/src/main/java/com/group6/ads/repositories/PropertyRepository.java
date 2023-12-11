package com.group6.ads.repositories;

import com.group6.ads.entities.Properties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Properties, Integer> {
}
