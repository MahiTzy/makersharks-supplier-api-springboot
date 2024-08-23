package com.makersharks.supplier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.makersharks.supplier.entities.Supplier;
import com.makersharks.supplier.exceptions.SupplierNotFoundException;
import com.makersharks.supplier.repository.SupplierRepository;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Page<Supplier> getSuppliers(String location, String natureOfBusiness,
            String manufacturingProcess, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Supplier> suppliers = supplierRepository
                .findByLocationAndNatureOfBusinessAndManufacturingProcessesContains(
                        location, natureOfBusiness, manufacturingProcess, pageRequest);
        if (suppliers.isEmpty()) {
            throw new SupplierNotFoundException("No suppliers found with the given criteria.");
        }

        return suppliers;
    }
}
