package com.group6.ads.controllers.contracts;

import com.group6.ads.controllers.contracts.models.ContractRequest;
import com.group6.ads.controllers.contracts.models.ContractUpdateRequest;
import com.group6.ads.controllers.properties.models.PropertyRequest;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.repositories.database.properties.Property;
import com.group6.ads.services.contracts.ContractService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/contracts")
public class ContractController {
    @NonNull
    final ContractService contractService;

    @GetMapping("")
    ResponseEntity<List<Contract>> getAllContracts() {
        return ResponseEntity.status(HttpStatus.OK).body(contractService.findAll());
    }

    @PostMapping("")
    ResponseEntity<Contract> createContract(@RequestBody @Valid ContractRequest contractRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.createContract(contractRequest));
    }

    @PutMapping("/{id}")
    ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody @Valid ContractUpdateRequest contractRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.updateContract(id, contractRequest));
    }
}
