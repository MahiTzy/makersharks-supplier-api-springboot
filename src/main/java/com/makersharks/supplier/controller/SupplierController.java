package com.makersharks.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makersharks.supplier.configuration.SupplierConfig;
import com.makersharks.supplier.entities.Supplier;
import com.makersharks.supplier.service.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin("*")
@Tag(name = "Supplier", description = "The Supplier API")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    @Operation(summary = "Query suppliers", description = "Search for suppliers based on location, nature of business, and manufacturing process.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved suppliers"),
            @ApiResponse(responseCode = "404", description = "No suppliers found")
    })
    public Page<Supplier> querySuppliers(@RequestParam String location,
            @RequestParam String natureOfBusiness,
            @RequestParam String manufacturingProcess,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (location.isEmpty()) {
            throw new IllegalArgumentException("Location must not be empty");
        }
        if (natureOfBusiness.isEmpty()) {
            throw new IllegalArgumentException("Nature of business must not be empty");
        }
        if (manufacturingProcess.isEmpty()) {
            throw new IllegalArgumentException("Manufacturing process must not be empty");
        }

        location = SupplierConfig.inputSanitizer(location);
        natureOfBusiness = SupplierConfig.inputSanitizer(natureOfBusiness);
        manufacturingProcess = SupplierConfig.inputSanitizer(manufacturingProcess);

        return supplierService.getSuppliers(location, natureOfBusiness, manufacturingProcess, page, size);
    }

}
