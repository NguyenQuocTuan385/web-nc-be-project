package com.group6.ads.services.contracts;

import com.group6.ads.controllers.contracts.models.ContractRequest;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.repositories.database.contracts.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService{
    private final ContractRepository contractRepository;
    private final AdvertiseRepository advertiseRepository;

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract createContract(ContractRequest contractRequest) {
        Advertise advertiseOfContract = advertiseRepository
                .findById(contractRequest.getAdvertiseId())
                .orElse(null);

        Contract newContract = Contract.builder()
                .startAt(contractRequest.getStartAt())
                .endAt(contractRequest.getEndAt())
                .companyName(contractRequest.getCompanyName())
                .companyEmail(contractRequest.getCompanyEmail())
                .companyPhone(contractRequest.getCompanyPhone())
                .companyAddress(contractRequest.getCompanyAddress())
                .image(contractRequest.getImage())
                .status(0)
                .advertises(advertiseOfContract)
                .build();
        return contractRepository.save(newContract);
    }
}
