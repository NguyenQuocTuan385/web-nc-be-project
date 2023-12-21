package com.group6.ads.repositories.database.contracts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query("""
            SELECT c
            FROM Contract c
            WHERE c.status = %:status% AND (c.companyName LIKE %:search% OR c.companyEmail LIKE %:search% OR c.companyPhone LIKE %:search%)
            """)
    Page<Contract> findAll(String search, Integer status, Pageable pageable);

    @Query("""
            SELECT c
            FROM Contract c
            WHERE (c.companyName LIKE %:search% OR c.companyEmail LIKE %:search% OR c.companyPhone LIKE %:search%)
            """)
    Page<Contract> findAll(String search, Pageable pageable);
}
