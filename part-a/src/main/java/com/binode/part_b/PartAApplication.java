package com.binode.part_b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PartAApplication {

	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(PartAApplication.class, args);
		ICustomerService customerService = context.getBean(ICustomerService.class);

		customerService.addCustomer("Frank Brown", "fbrown@acme.com", "mainstreet 5", "Chicago", "60613");

		IProductService productService = context.getBean(IProductService.class);
		productService.addProduct(1,"Iphone X", 5000, "abc@xyz.com");

	}

}
