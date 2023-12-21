package com.group6.ads.repositories.database.reports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * com.group6.ads.repositories.database.reports
 * Create by Dang Ngoc Tien
 * Date 12/17/2023 - 12:49 AM
 * Description: ...
 */
public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query("""
            SELECT r
            FROM Report r
            WHERE (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAll(String search, Pageable pageable);
}
