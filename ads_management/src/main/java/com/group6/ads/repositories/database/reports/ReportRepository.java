package com.group6.ads.repositories.database.reports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
            WHERE r.guestEmail = :email AND r.advertise.location.id = :locationId
            AND (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAllByEmailAndLocation(Integer locationId, String email, String search, Pageable pageable);

    @Query("""
            SELECT r
            FROM Report r
            WHERE (r.property.id = :propertyId) AND (r.reportTypeName = :reportTypeName or :reportTypeName is null)
            AND (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAllByPropertyId(Integer propertyId, String reportTypeName, String search, Pageable pageable);

    @Query("""
            SELECT r
            FROM Report r
            WHERE (r.guestEmail = :email AND r.advertise.location.id = :locationId) AND (r.status = 1 OR r.status = 2)
            AND (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAllReportExceptSuccessfullyByEmailAndLocation(Integer locationId, String email, String search, Pageable pageable);

    @Query("""
            SELECT r
            FROM Report r
            WHERE r.guestEmail = :email AND (r.reportTypeName = :reportTypeName or :reportTypeName is null)
            AND (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAllByEmailAndReportTypeName(String email, String reportTypeName, String search, Pageable pageable);

    @Query("""
            SELECT r
            FROM Report r
            WHERE (r.advertise.location.id = :locationId)
            AND (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAllByLocationId(Integer locationId, String search, Pageable pageable);

    @Query("""
            SELECT r
            FROM Report r
            WHERE (r.advertise.location.id = :locationId) AND (r.status = 1 OR r.status = 2)
            AND (r.fullName LIKE %:search% OR r.email LIKE %:search% OR r.phone LIKE %:search%)
            """)
    Page<Report> findAllReportsExceptSuccessfullyByLocation(Integer locationId, String search, Pageable pageable);

}
