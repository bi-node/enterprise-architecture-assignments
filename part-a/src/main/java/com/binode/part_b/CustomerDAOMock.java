package com.binode.part_b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("mock")
public class CustomerDAOMock implements ICustomerDAO {
		@Autowired
	private ILogger logger;

	public CustomerDAOMock(ILogger logger) {
		this.logger = logger;
	}

	@Override
	public void save(Customer customer) {
		// simple sleep
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CustomerDAO Mock: saving customer " + customer.getName());
		logger.log("Customer is saved in the Mock DB: " + customer.getName());
	}
}
