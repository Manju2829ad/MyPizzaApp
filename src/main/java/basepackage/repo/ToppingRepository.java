package basepackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import basepackage.model.Topping;

public interface ToppingRepository extends JpaRepository<Topping, Long> {

}
