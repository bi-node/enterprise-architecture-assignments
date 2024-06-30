package repositories;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByAddressZip(String addressZip);
    List<Customer> findAllByAddressCity(@Param("city") String addressCity);

    @Query("select c.firstName,c.lastName from Customer c where c.address.street=:street")
    List<Object[]> findByStreet(@Param("street") String street);
}
