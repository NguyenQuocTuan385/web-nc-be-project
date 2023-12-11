package com.group6.ads.services.contracts;

import com.group6.ads.controllers.contracts.models.ContractRequest;
import com.group6.ads.controllers.properties.models.PropertyRequest;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.repositories.database.properties.Property;

import java.util.List;

public interface ContractService {
    List<Contract> findAll();

    Contract createContract(ContractRequest contractRequest);
}
