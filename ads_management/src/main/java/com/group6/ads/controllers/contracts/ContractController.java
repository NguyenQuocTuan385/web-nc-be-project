package com.group6.ads.controllers.contracts;

import com.group6.ads.controllers.contracts.models.ContractRequest;
import com.group6.ads.controllers.contracts.models.ContractUpdateRequest;
import com.group6.ads.controllers.contracts.models.ContractUpdateStatusRequest;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.services.contracts.ContractService;
import com.group6.ads.util.PageRequestCustom;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/")
@CrossOrigin("*")
public class ContractController {
    @NonNull
    final ContractService contractService;

    @GetMapping("properties/{propertyId}/contracts")
    ResponseEntity<Page<Contract>> getAllContracts(
            @PathVariable Integer propertyId,
            @RequestParam(required = false, value = "status", defaultValue = "")
            Integer status,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize
    ) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(contractService.findAll(propertyId, search, status, pageRequestCustom));
    }

    @GetMapping("properties/contracts")
    ResponseEntity<Page<Contract>> getContractsWithPropertyIds(
            @RequestParam(required = false, value = "propertyId[]", defaultValue = "")
            Integer[] propertyId,
            @RequestParam(required = false, value = "parentId[]", defaultValue = "")
            Integer[] parentId,
            @RequestParam(required = false, value = "status", defaultValue = "")
            Integer status,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize
    ) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);

        if(parentId.length == 0)
            parentId = null;
        if(propertyId.length == 0)
            propertyId = null;
        return ResponseEntity.status(HttpStatus.OK).body(contractService.findAll(propertyId, parentId, search, status, pageRequestCustom));
    }


    @GetMapping("advertises/{advertiseId}/contracts")
    ResponseEntity<Page<Contract>> getAllContractsByAdvertise(
            @PathVariable Integer advertiseId,
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1)
            Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10")
            Integer pageSize
    ) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(contractService.findByAdvertiseId(advertiseId, search, pageRequestCustom));
    }
    @GetMapping("contracts/{id}")
    ResponseEntity<Contract> getContractById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(contractService.findById(id));
    }

    @PostMapping("contracts")
    ResponseEntity<Contract> createContract(@RequestBody @Valid ContractRequest contractRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.createContract(contractRequest));
    }


    @PutMapping("contracts/{id}")
    ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody @Valid ContractUpdateRequest contractRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(contractService.updateContract(id, contractRequest));
    }

    @PutMapping("contracts/{id}/status")
    ResponseEntity<Contract> updateStatusContract(@PathVariable Long id, @RequestBody @Valid ContractUpdateStatusRequest contractRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(contractService.updateStatusContract(id, contractRequest));
    }

    @DeleteMapping("contracts/{id}")
    ResponseEntity<String> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success delete contract with id " + id);
    }
}
