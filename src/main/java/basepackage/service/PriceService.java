package basepackage.service;

import java.util.List;

import basepackage.dto.PriceDTO;
import basepackage.model.Price;

public interface PriceService {

	


	List<PriceDTO> findByPizzaId(Long pizzaId);
}
