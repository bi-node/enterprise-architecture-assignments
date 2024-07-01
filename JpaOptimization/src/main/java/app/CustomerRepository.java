package app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
     @Query("select   c from Customer c join fetch c.account")
    public List<Customer> findA();


}




