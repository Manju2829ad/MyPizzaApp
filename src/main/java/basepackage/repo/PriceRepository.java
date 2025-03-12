package basepackage.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import basepackage.model.Price;




public interface PriceRepository  extends JpaRepository<Price, Long>{

	
	List<Price>  findByPizzaId(Long pizzaId);
	
}
