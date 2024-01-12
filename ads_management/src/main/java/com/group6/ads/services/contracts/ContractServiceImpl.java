package com.group6.ads.services.contracts;

import com.group6.ads.controllers.contracts.models.ContractRequest;
import com.group6.ads.controllers.contracts.models.ContractUpdateRequest;
import com.group6.ads.controllers.contracts.models.ContractUpdateStatusRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.repositories.database.contracts.ContractRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final AdvertiseRepository advertiseRepository;

    @Override
    public Page<Contract> findAll(Integer propertyId, String search, Integer status, PageRequestCustom pageRequestCustom) {
        return contractRepository.findAll(propertyId, search, status, pageRequestCustom.pageRequest());
    }

    @Override
    public Page<Contract> findAll(Integer[] propertyId, Integer[] parentId, String search, Integer status, PageRequestCustom pageRequestCustom) {
        return contractRepository.findAll(propertyId, parentId, search, status, pageRequestCustom.pageRequest());
    }

    @Override
    public Page<Contract> findByAdvertiseId(Integer advertiseId, String search, PageRequestCustom pageRequestCustom) {
        return contractRepository.findByAdvertiseId(advertiseId, search, pageRequestCustom.pageRequest());
    }

    @Override
    public Contract findContractLicensingByAdvertiseId(Integer advertiseId) {
        Contract contract = contractRepository.findContractLicensingByAdvertiseId(advertiseId);
        if(contract != null) {
            return contract;
        }
        throw new NotFoundException("Contract not found");
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
                .images(contractRequest.getImages())
                .status(0)
                .advertise(advertiseOfContract)
                .build();
        return contractRepository.save(newContract);
    }

    @Override
    public Contract findById(Long id) {
        return contractRepository
                .findById(Math.toIntExact(id))
                .orElseThrow(() -> new NotFoundException("Not found contract with id " + id));
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
        oldContract.setImages(contractRequest.getImages());
        oldContract.setStatus(contractRequest.getStatus());
        oldContract.setAdvertise(advertiseOfContract);

        return contractRepository.save(oldContract);
    }

    @Override
    public Contract updateStatusContract(Long id, ContractUpdateStatusRequest contractRequest) {
        Contract oldContract = contractRepository.findById(Math.toIntExact(id)).orElse(null);

        if (oldContract == null) {
            throw new NotFoundException("Not found contract with id " + id);
        }

        oldContract.setStatus(contractRequest.getStatus());

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
