package com.group6.ads.repositories.database.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * com.group6.ads.repositories.database.images
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 12:48 AM
 * Description: ...
 */
public interface ImageRepository extends JpaRepository<Image, Integer> {
    void deleteByReportId(int reportId);
}
