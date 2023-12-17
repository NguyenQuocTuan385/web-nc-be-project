package com.group6.ads.repositories.database.report.forms;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * com.group6.ads.repositories.database.report.forms
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 12:58 PM
 * Description: ...
 */
@Repository
public interface ReportFormRepository extends JpaRepository<ReportForm, Integer> {
    @Query("SELECT rf FROM ReportForm rf WHERE rf.name LIKE %:search% OR rf.description LIKE %:search%")
    Page<ReportForm> findAll(String search, Pageable pageable);
}
