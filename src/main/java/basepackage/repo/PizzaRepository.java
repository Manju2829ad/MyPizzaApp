package basepackage.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import basepackage.dto.PizzaDTO;
import basepackage.model.Pizza;


public interface PizzaRepository extends JpaRepository<Pizza, Long> {

	
	List<Pizza> getPizzaByCategory(String category);
}
