package com.makersharks.supplier.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.makersharks.supplier.entities.Supplier;
import com.makersharks.supplier.repository.SupplierRepository;

@SpringBootTest
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    public SupplierServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSuppliers() {
        // Setup mock supplier data
        Supplier supplier = new Supplier();
        supplier.setCompanyName("Test Company");
        supplier.setLocation("India");
        supplier.setNatureOfBusiness("small_scale");
        supplier.setManufacturingProcesses(Collections.singletonList("3d_printing"));

        Page<Supplier> mockPage = new PageImpl<>(Collections.singletonList(supplier));
        
        // Define behavior of repository mock
        when(supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcessesContains(
                "India", "small_scale", "3d_printing", PageRequest.of(0, 10)))
                .thenReturn(mockPage);

        // Call service method
        Page<Supplier> result = supplierService.getSuppliers("India", "small_scale", "3d_printing", 0, 10);

        // Assert the results
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Company", result.getContent().get(0).getCompanyName());
    }
}
