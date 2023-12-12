package com.group6.ads.services.contracts;

import com.group6.ads.controllers.contracts.models.ContractRequest;
import com.group6.ads.controllers.contracts.models.ContractUpdateRequest;
import com.group6.ads.repositories.database.contracts.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> findAll();

    Contract createContract(ContractRequest contractRequest);

    Contract updateContract(Long id, ContractUpdateRequest contractRequest);

    void deleteContract(Long id);
}
