package com.group6.ads.repositories.database.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * com.group6.ads.repositories.database.images
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 12:48 AM
 * Description: ...
 */
public interface ImageRepository extends JpaRepository<Image, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM images WHERE location_id=:id")
    List<Image> findImageByLocationId(@Param("id") Integer id);
}
