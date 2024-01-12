package com.group6.ads.repositories.database.contracts;

import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query("""
            SELECT c
            FROM Contract c
            WHERE (c.advertise.location.property.id = :propertyId OR c.advertise.location.property.propertyParent.id = :propertyId)
                AND (c.status = :status OR :status IS NULL)
                AND (c.companyName LIKE %:search% OR c.companyEmail LIKE %:search% OR c.companyPhone LIKE %:search% OR c.companyAddress LIKE %:search%)
            """)
    Page<Contract> findAll(Integer propertyId, String search, Integer status, Pageable pageable);

    @Query("""
            SELECT c
            FROM Contract c
            WHERE (((c.advertise.location.property.id IN (:propertyId) OR :propertyId IS NULL) AND c.advertise.location.property.propertyParent.id IN (:parentId)) OR  :parentId IS NULL)
                AND (c.status = :status OR :status IS NULL)
                AND (c.companyName LIKE %:search% OR c.companyEmail LIKE %:search% OR c.companyPhone LIKE %:search% OR c.companyAddress LIKE %:search%)
            """)
    Page<Contract> findAll(Integer[] propertyId, Integer[] parentId, String search, Integer status, Pageable pageable);

    @Query("""
            SELECT c
            FROM Contract c, Advertise a
            WHERE c.advertise.id = :advertiseId AND c.status = 1
            AND (c.companyName LIKE %:search% OR c.companyEmail LIKE %:search% OR c.companyPhone LIKE %:search%)
            """)
    Page<Contract> findByAdvertiseId(Integer advertiseId, String search, Pageable pageable);

    @Query("""
            SELECT c
            FROM Contract c, Advertise a
            WHERE c.advertise.id = :advertiseId AND c.status = 1
            AND (c.companyName LIKE %:search% OR c.companyEmail LIKE %:search% OR c.companyPhone LIKE %:search%)
            """)
    Page<Contract> findAll(String search, Pageable pageable);

    @Query("""
            SELECT c FROM Contract c WHERE c.advertise.id = :advertiseId AND c.status = 1
            """)
    Contract findContractLicensingByAdvertiseId(Integer advertiseId);
}
