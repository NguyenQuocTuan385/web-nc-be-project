package com.group6.ads.repositories.database.report.forms;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group6.ads.repositories.database.reports.Report;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * com.group6.ads.repositories.database.report.forms
 * Create by Dang Ngoc Tien
 * Date 12/12/2023 - 12:54 PM
 * Description: ...
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "report_forms")
public class ReportForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "reportForm", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Report> reports;
}
