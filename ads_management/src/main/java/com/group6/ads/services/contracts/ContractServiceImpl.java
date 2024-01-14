package com.group6.ads.services.contracts;

import com.group6.ads.controllers.admin.contracts.models.ContractRequest;
import com.group6.ads.controllers.admin.contracts.models.ContractUpdateRequest;
import com.group6.ads.controllers.admin.contracts.models.ContractUpdateStatusRequest;
import com.group6.ads.exceptions.NotFoundException;
import com.group6.ads.repositories.database.advertises.Advertise;
import com.group6.ads.repositories.database.advertises.AdvertiseRepository;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.repositories.database.contracts.ContractRepository;
import com.group6.ads.util.PageRequestCustom;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final AdvertiseRepository advertiseRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Page<Contract> findAll(Integer propertyId, String search, Integer status, PageRequestCustom pageRequestCustom) {
        logger.info("(findAll) Find All Contracts with propertyId: {}, search: {}, status: {}", propertyId, search, status);

        return contractRepository.findAll(propertyId, search, status, pageRequestCustom.pageRequest());
    }

    @Override
    public Page<Contract> findAll(Integer[] propertyId, Integer[] parentId, String search, Integer status, PageRequestCustom pageRequestCustom) {
        logger.info("(findAll) Find All Contracts with propertyId: {}, parentId: {}, search: {}, status: {}", Arrays.toString(propertyId), Arrays.toString(parentId), search, status);

        return contractRepository.findAll(propertyId, parentId, search, status, pageRequestCustom.pageRequest());
    }

    @Override
    public Page<Contract> findByAdvertiseId(Integer advertiseId, String search, PageRequestCustom pageRequestCustom) {
        logger.info("(findByAdvertiseId) Find Contracts With advertiseId {}", advertiseId);

        return contractRepository.findByAdvertiseId(advertiseId, search, pageRequestCustom.pageRequest());
    }

    @Override
    public Contract findContractLicensingByAdvertiseId(Integer advertiseId) {
        logger.info("(findContractLicensingByAdvertiseId) Find Contracts With status {}, advertiseId {}", 1, advertiseId);

        Contract contract = contractRepository.findContractLicensingByAdvertiseId(advertiseId);
        if(contract != null) {
            logger.info("Found Contracts With advertiseId {}, contract {}", advertiseId, contract);
            return contract;
        }

        logger.warn("Not found Contracts With advertiseId {}", advertiseId);
        throw new NotFoundException("Contract not found");
    }

    @Override
    public Contract createContract(ContractRequest contractRequest) {
        logger.info("(createContract) Create new contracts with data " + contractRequest);
        int adId = contractRequest.getAdvertiseId();

        Advertise advertiseOfContract = advertiseRepository
                .findById(adId)
                .orElse(null);

        if(advertiseOfContract == null){
            logger.warn("(createContract) Not found advertise with id " + adId);
            throw new NotFoundException("Not found avertiseId " + adId);
        }

        try {
            Contract newContract = Contract.builder()
                    .startAt(contractRequest.getStartAt())
                    .endAt(contractRequest.getEndAt())
                    .companyName(contractRequest.getCompanyName())
                    .companyEmail(contractRequest.getCompanyEmail())
                    .companyPhone(contractRequest.getCompanyPhone())
                    .companyAddress(contractRequest.getCompanyAddress())
                    .images(contractRequest.getImages())
                    .status(2)
                    .advertise(advertiseOfContract)
                    .createdAt(LocalDateTime.now())
                    .build();

            logger.info("Update contract with data {} successful", newContract);
            return contractRepository.save(newContract);
        }

        catch (Exception e) {
            logger.error(e.getMessage());
        }

       return null;
    }

    @Override
    public Contract findById(Long id) {
        logger.info("(findById) Find contract with id {}", id);

        return contractRepository
                .findById(Math.toIntExact(id))
                .orElseThrow(() -> {
                    logger.error("(findById) Not found contract with id {}", id);
                    return new NotFoundException("Not found contract with id " + id);
                });
    }

    @Override
    public Contract updateContract(Long id, ContractUpdateRequest contractRequest) {
        int adId = contractRequest.getAdvertiseId();
        Advertise advertiseOfContract = advertiseRepository
                .findById(contractRequest.getAdvertiseId())
                .orElse(null);
        Contract oldContract = contractRepository.findById(Math.toIntExact(id)).orElse(null);

        logger.info("(updateContract) Update contract with data {}", contractRequest);

        if(advertiseOfContract == null){
            logger.warn("(updateContract) Not found advertise with id {}", adId);
            throw new NotFoundException("Not found avertiseId " + adId);
        }

        if (oldContract == null) {
            logger.warn("(updateContract) Can't find contract with id {}", id);
            throw new NotFoundException("Not found contract with id " + id);
        }

        try {
            oldContract.setStartAt(contractRequest.getStartAt());
            oldContract.setEndAt(contractRequest.getEndAt());
            oldContract.setCompanyName(contractRequest.getCompanyName());
            oldContract.setCompanyEmail(contractRequest.getCompanyEmail());
            oldContract.setCompanyPhone(contractRequest.getCompanyPhone());
            oldContract.setCompanyAddress(contractRequest.getCompanyAddress());
            oldContract.setImages(contractRequest.getImages());
            oldContract.setStatus(contractRequest.getStatus());
            oldContract.setAdvertise(advertiseOfContract);
        }
        catch (Exception e) {
            logger.error("(updateContract) {}", e.getMessage());

            return null;
        }

        return contractRepository.save(oldContract);
    }

    @Override
    public Contract updateStatusContract(Long id, ContractUpdateStatusRequest contractRequest) {
        Contract oldContract = contractRepository.findById(Math.toIntExact(id)).orElse(null);

        logger.info("(updateStatusContract) Update contract have id {} with new status {}", id, contractRequest.getStatus());

        if (oldContract == null) {
            logger.warn("(updateStatusContract) Can't find contract with id {}", id);
            throw new NotFoundException("Not found contract with id " + id);
        }

        oldContract.setStatus(contractRequest.getStatus());

        return contractRepository.save(oldContract);
    }

    @Override
    public void deleteContract(Long id) {
        logger.info("(deleteContract) Delete contract have id " + id);

        if (contractRepository.existsById(Math.toIntExact(id))){
            try {
                contractRepository.deleteById(Math.toIntExact(id));
                logger.info("(deleteContract) SUCCESS Delete contract have id " + id );
            }
            catch (Exception e) {
                logger.error("(deleteContract) {}", e.getMessage());
            }
        }
        else{
            logger.warn("(deleteContract) contract not found with id " + id);
            throw new NotFoundException("Can't Delete, can't find contract with id " + id);
        }
    }
}
