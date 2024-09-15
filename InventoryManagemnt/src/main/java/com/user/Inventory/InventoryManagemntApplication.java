package com.user.Inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients
@CrossOrigin("http://localhost:4200")
public class InventoryManagemntApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagemntApplication.class, args);
	}

}
