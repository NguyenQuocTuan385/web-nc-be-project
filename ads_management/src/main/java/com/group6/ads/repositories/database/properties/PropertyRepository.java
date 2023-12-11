package com.group6.ads.repositories.database.properties;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    List<Property> findAllByPropertyParentId(Integer propertyParentId);

    Boolean existsByPropertyParentId(Integer propertyParentId);
}
