package com.group6.ads.services.contracts;

import com.group6.ads.controllers.contracts.models.ContractRequest;
import com.group6.ads.controllers.contracts.models.ContractUpdateRequest;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.util.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContractService {
    Page<Contract> findAll(Integer propertyId, String search, Integer status, PageRequestCustom pageRequestCustom);
    Page<Contract> findByAdvertiseId(Integer advertiseId, String search, PageRequestCustom pageRequestCustom);

    Contract findByAdvertiseIdOne(Integer advertiseId);

    Contract createContract(ContractRequest contractRequest);

    Contract updateContract(Long id, ContractUpdateRequest contractRequest);
    void deleteContract(Long id);

    Contract getContractById(Long id);
}
