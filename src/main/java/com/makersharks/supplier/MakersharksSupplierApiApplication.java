package com.makersharks.supplier;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.makersharks.supplier.entities.Supplier;
import com.makersharks.supplier.repository.SupplierRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Supplier API",
        version = "1.0",
        description = "API for managing suppliers",
        contact = @Contact(
            name = "Mohit Singh",
            email = "mohitofficial229@gmail.com",
            url = "https://portfolio-mohit-one.vercel.app/"
        )
    )
)
public class MakersharksSupplierApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakersharksSupplierApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(SupplierRepository supplierRepository) {
		return args -> {

			// hardcoded suppliers to make up to 100 entries
			for (int i = 1; i <= 100; i++) {
				supplierRepository.save(new Supplier(
						null, "Company" + i,
						"https://company" + i + ".com",
						getLocation(i),
						getNatureOfBusiness(i),
						Arrays.asList(getManufacturingProcess(i), getManufacturingProcess((i + 1) % 4))));
			}
		};
	}

	private String getLocation(int index) {
		switch (index % 5) {
			case 0:
				return "Bangalore";
			case 1:
				return "Pune";
			case 2:
				return "Mumbai";
			case 3:
				return "Delhi";
			default:
				return "Chennai";
		}
	}

	private String getNatureOfBusiness(int index) {
		switch (index % 3) {
			case 0:
				return "small_scale";
			case 1:
				return "medium_scale";
			default:
				return "large_scale";
		}
	}

	private String getManufacturingProcess(int index) {
		switch (index % 4) {
			case 0:
				return "moulding";
			case 1:
				return "3d_printing";
			case 2:
				return "casting";
			default:
				return "coating";
		}
	}

}
