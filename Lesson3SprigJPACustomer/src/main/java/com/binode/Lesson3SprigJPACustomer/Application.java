package com.binode.Lesson3SprigJPACustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);




	}

	@Override
	public void run(String... args) throws Exception {
		// Save customers
		customerRepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
		customerRepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
		customerRepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
		customerRepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
		customerRepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

		// Fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// Fetch a customer by ID
		Optional<Customer> custOpt = customerRepository.findById(1L);
		if (custOpt.isPresent()) {
			Customer customer = custOpt.get();
			System.out.println("Customer found with findById(1L):");
			System.out.println("---------------------------------");
			System.out.println(customer);
			System.out.println();
		} else {
			System.out.println("Customer with ID 1 not found.");
			System.out.println();
		}
	}
}
