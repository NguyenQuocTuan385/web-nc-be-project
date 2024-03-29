package com.group6.ads.controllers.client.contracts;

import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.services.contracts.ContractService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/")
@CrossOrigin("*")
@Tag(name = "Contract Client", description = "Contract Client API")
public class ContractClientController {
    @NonNull
    final ContractService contractService;

    @GetMapping("/contracts-client/advertises/{advertiseId}")
    ResponseEntity<?> getContractByAdvertiseId(@PathVariable Integer advertiseId) {
        try {
            Contract contract = contractService.findContractLicensingByAdvertiseId(advertiseId);
            return ResponseEntity.status(HttpStatus.OK).body(contract);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
