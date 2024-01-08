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
            WHERE (r.reportTypeName = :reportTypeName or :reportTypeName is null) AND (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAll(String reportTypeName,String search, Pageable pageable);

    @Query("""
            SELECT r
            FROM Report r
            WHERE (r.advertise.location.property.id IN (:propertyId) OR :propertyId IS NULL)
                AND (r.advertise.location.property.propertyParent.id IN (:parentId) OR  :parentId IS NULL)
                AND (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAll(Integer[] propertyId, Integer[] parentId, String search, Pageable pageable);

    @Query("""
            SELECT r
            FROM Report r
            WHERE r.email = :email AND r.advertise.location.id = :locationId
            AND (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAllByEmailAndLocation(Integer locationId, String email, String search, Pageable pageable);
}
