package com.binode.Lesson3SprigJPACustomer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // No additional methods needed for basic CRUD operations
}
