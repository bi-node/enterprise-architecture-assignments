package repositories;

import domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("select o from Order o where o.status=:status")
    List<Order> findByStatus(@Param("status") String status);

    @Query("select o.orderNumber from Order o where o.customer.address.city=:city")
    List<String> findOrdersFromCity(@Param("city") String city);
}
