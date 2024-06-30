package repositories.specification;

import domain.Customer;
import org.springframework.data.jpa.domain.Specification;


public class CustomerSpecification {
    public static Specification<Customer> customerFromAdressCity(String city
    ) {
        return (root,query,cb)->
               cb.like(root.get("address").get("city"), "%"+city+"%");
    }
}
