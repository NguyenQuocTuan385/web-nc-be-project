package com.group6.ads.repositories.database.contracts;

import com.group6.ads.repositories.database.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
