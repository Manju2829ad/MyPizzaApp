package basepackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import basepackage.dto.PizzaDTO;
import basepackage.dto.PriceDTO;
import basepackage.model.Price;
import basepackage.service.PriceService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/price/")
public class PriceController {

	
	
	@Autowired
	private   PriceService  priceService;
	
	@GetMapping("/get/{pizzaId}")
	public  ResponseEntity<List<PriceDTO>> getPriceByPizzaId(@PathVariable Long pizzaId){
		
		
		try {
			
			         List<PriceDTO> priceDTO=priceService.findByPizzaId(pizzaId);
			         
			         return ResponseEntity.ok(priceDTO);
			         
		} catch( Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	
	
}
