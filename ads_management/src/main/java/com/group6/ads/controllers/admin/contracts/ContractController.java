package com.group6.ads.controllers.admin.contracts;

import com.group6.ads.controllers.admin.contracts.models.ContractRequest;
import com.group6.ads.controllers.admin.contracts.models.ContractUpdateRequest;
import com.group6.ads.controllers.admin.contracts.models.ContractUpdateStatusRequest;
import com.group6.ads.repositories.database.contracts.Contract;
import com.group6.ads.services.contracts.ContractService;
import com.group6.ads.util.PageRequestCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/")
@CrossOrigin("*")
@Tag(name = "Contracts ", description = "Contract API")

public class ContractController {
    @NonNull
    final ContractService contractService;


    @GetMapping("properties/{propertyId}/contracts")
    @Operation(summary = "Get All contracts", parameters = {
            @Parameter(name = "propertyId", description = "ID của phường, quận muốn tìm", example = "3"),
            @Parameter(name = "status", description = "Trạng thái hợp đồng", example = ""),
            @Parameter(name = "search", description = "Từ khóa muốn tìm", example = "3"),
            @Parameter(name = "current", description = "trang hiện tại", example = "1"),
            @Parameter(name = "pageSize", description = "Số lượng muốn hiển thị", example = "5")
    })
    ResponseEntity<?> getAllContracts(
            @PathVariable Integer propertyId,
            @RequestParam(required = false, value = "status", defaultValue = "") Integer status,
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(contractService.findAll(propertyId, search, status, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Lấy tất cả hợp đồng với id phường và id quận", parameters = {
            @Parameter(name = "propertyId[]", description = "ID của phường muốn tìm", example = "[1]"),
            @Parameter(name = "parentId[]", description = "ID của quận", example = "[2]"),
            @Parameter(name = "status", description = "Trạng thái hợp đồng", example = ""),
            @Parameter(name = "search", description = "Từ khóa muốn tìm", example = "3"),
            @Parameter(name = "current", description = "trang hiện tại", example = "1"),
            @Parameter(name = "pageSize", description = "Số lượng muốn hiển thị", example = "5")
    })
    @GetMapping("properties/contracts")
    ResponseEntity<?> getContractsWithPropertyIds(
            @RequestParam(required = false, value = "propertyId[]", defaultValue = "") Integer[] propertyId,
            @RequestParam(required = false, value = "parentId[]", defaultValue = "") Integer[] parentId,
            @RequestParam(required = false, value = "status", defaultValue = "") Integer status,
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            if (parentId.length == 0)
                parentId = null;
            if (propertyId.length == 0)
                propertyId = null;
            return ResponseEntity.status(HttpStatus.OK)
                    .body(contractService.findAll(propertyId, parentId, search, status, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("advertises/{advertiseId}/contracts")
    @Operation(summary = "Lấy tất cả hợp đồng với id quảng cáo", parameters = {
            @Parameter(name = "advertiseId", description = "ID của quảng cáo muốn tìm", example = "1"),
            @Parameter(name = "search", description = "Từ khóa muốn tìm", example = "3"),
            @Parameter(name = "current", description = "trang hiện tại", example = "1"),
            @Parameter(name = "pageSize", description = "Số lượng muốn hiển thị", example = "5")
    })
    ResponseEntity<?> getAllContractsByAdvertise(
            @PathVariable Integer advertiseId,
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "current", defaultValue = "1") @Min(1) Integer currentPage,
            @RequestParam(required = false, value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            PageRequestCustom pageRequestCustom = PageRequestCustom.of(currentPage, pageSize);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(contractService.findByAdvertiseId(advertiseId, search, pageRequestCustom));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("contracts/{id}")
    @Operation(summary = "Lấy hợp đồng với id truyền vào", parameters = {
            @Parameter(name = "id", description = "ID của hợp đồng muốn tìm", example = "1")
    })
    ResponseEntity<?> getContractById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(contractService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("contracts/advertises/{advertiseId}")
    @Operation(summary = "Lấy hợp đồng với id của quảng cáo", parameters = {
            @Parameter(name = "advertiseId", description = "ID của quảng cáo muốn tìm", example = "1")
    })
    ResponseEntity<?> getContractByAdvertiseId(@PathVariable Integer advertiseId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(contractService.findContractLicensingByAdvertiseId(advertiseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("contracts")
    @Operation(summary = "Tạo hợp đồng mới")
    ResponseEntity<?> createContract(@RequestBody @Valid ContractRequest contractRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(contractService.createContract(contractRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("contracts/{id}")
    @Operation(summary = "Update hợp đồng với id ", parameters = {
            @Parameter(name = "id", description = "ID của hợp đồng muốn tìm", example = "1")
    })
    ResponseEntity<?> updateContract(@PathVariable Long id,
            @RequestBody @Valid ContractUpdateRequest contractRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(contractService.updateContract(id, contractRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("contracts/{id}/status")
    @Operation(summary = "Update status hợp đồng với id ", parameters = {
            @Parameter(name = "id", description = "ID của hợp đồng muốn tìm", example = "1")
    })
    ResponseEntity<?> updateStatusContract(@PathVariable Long id,
            @RequestBody @Valid ContractUpdateStatusRequest contractRequest) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(contractService.updateStatusContract(id, contractRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("contracts/{id}")
    @Operation(summary = "Delete hợp đồng với id ", parameters = {
            @Parameter(name = "id", description = "ID của hợp đồng muốn tìm", example = "1")
    })
    ResponseEntity<?> deleteContract(@PathVariable Long id) {
        try {
            contractService.deleteContract(id);
            return ResponseEntity.status(HttpStatus.OK).body("Success delete contract with id " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
