package basepackage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import basepackage.dto.PizzaDTO;
import basepackage.service.PizzaService;
import jakarta.websocket.server.PathParam;



@RestController
@RequestMapping("/api/pizza") 
public class PizzaController {

	
	
	@Autowired
	private PizzaService  pservice;
	
	
	@GetMapping("/get/{category}")
	public ResponseEntity<List<PizzaDTO>> getPizzaByCategory(@PathVariable String category) {
	    try {
	        List<PizzaDTO> pizzaDTO = pservice.getPizzaByCategory(category);
	        return ResponseEntity.ok(pizzaDTO);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return an appropriate response
	    }
	}


}
