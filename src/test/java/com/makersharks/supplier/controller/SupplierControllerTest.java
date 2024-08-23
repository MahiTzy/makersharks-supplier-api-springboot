package com.makersharks.supplier.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.makersharks.supplier.entities.Supplier;
import com.makersharks.supplier.repository.SupplierRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    public void testQuerySuppliers() throws Exception {
        // Insert a test supplier into the repository
        Supplier supplier = new Supplier();
        supplier.setCompanyName("Test Company");
        supplier.setLocation("India");
        supplier.setNatureOfBusiness("small_scale");
        supplier.setManufacturingProcesses(Collections.singletonList("3d_printing"));
        supplierRepository.save(supplier);

        // Perform the POST request
        mockMvc.perform(post("/api/supplier/query")
                .param("location", "India")
                .param("natureOfBusiness", "small_scale")
                .param("manufacturingProcess", "3d_printing")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"content\":[{\"companyName\":\"Test Company\"}]}"));
    }
}
