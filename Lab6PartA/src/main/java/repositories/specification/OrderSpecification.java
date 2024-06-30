package repositories.specification;

import domain.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {

    public static Specification<Order> hasInStatus(String status) {
        return
                (root,query,cb)->cb.like(root.get("status"), "%"+status+"%");
    }
}
