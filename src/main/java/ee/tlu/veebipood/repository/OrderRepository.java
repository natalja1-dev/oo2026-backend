package ee.tlu.veebipood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ee.tlu.veebipood.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
