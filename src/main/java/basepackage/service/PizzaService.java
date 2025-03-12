package basepackage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import basepackage.dto.PizzaDTO;



public interface PizzaService {

	


	List<PizzaDTO> getPizzaByCategory(String category);
	
	
}
