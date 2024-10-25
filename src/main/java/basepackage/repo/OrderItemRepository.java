package basepackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import basepackage.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
