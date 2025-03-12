package basepackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import basepackage.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
