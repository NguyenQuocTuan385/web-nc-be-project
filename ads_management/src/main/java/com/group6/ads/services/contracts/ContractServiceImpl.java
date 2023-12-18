package com.group6.ads.services.contracts;

import com.group6.ads.controllers.contracts.models.ContractRequest;
import com.group6.ads.controllers.contracts.models.ContractUpdateRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.repositories.database.contracts.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
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
                .imgUrl(contractRequest.getImage())
                .status(0)
                 .advertise(advertiseOfContract)
                .build();
        return contractRepository.save(newContract);
    }

    @Override
    public Contract updateContract(Long id, ContractUpdateRequest contractRequest) {
        Advertise advertiseOfContract = advertiseRepository
                .findById(contractRequest.getAdvertiseId())
                .orElse(null);
        Contract oldContract = contractRepository.findById(Math.toIntExact(id)).orElse(null);

        if (oldContract == null) {
            throw new NotFoundException("Not found contract with id " + id);
        }

        oldContract.setStartAt(contractRequest.getStartAt());
        oldContract.setEndAt(contractRequest.getEndAt());
        oldContract.setCompanyName(contractRequest.getCompanyName());
        oldContract.setCompanyEmail(contractRequest.getCompanyEmail());
        oldContract.setCompanyPhone(contractRequest.getCompanyPhone());
        oldContract.setCompanyAddress(contractRequest.getCompanyAddress());
        oldContract.setImgUrl(contractRequest.getImage());
        oldContract.setStatus(contractRequest.getStatus());
         oldContract.setAdvertise(advertiseOfContract);

        return contractRepository.save(oldContract);
    }

    @Override
    public void deleteContract(Long id) {
        if (contractRepository.existsById(Math.toIntExact(id)))
            contractRepository.deleteById(Math.toIntExact(id));
        else
            throw new NotFoundException("Can't Delete, can't find contract with id " + id);
    }
}