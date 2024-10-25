package basepackage.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import basepackage.model.Price;
import basepackage.model.Topping;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO {
	
    private Long id;
    private String name;
	

    
    private String category;
	
    private String image;  // Updated from Byte to String
	

    private String description;
	

         // Mark size as an embeddable collection
	    private  String sizes;
	

          // Mark crust as an embeddable collection
	    private  String  crust;

	    

    private Boolean isVeg;  // Changed to Boolean for better representation


    private List<Topping> toppings;

 
    private List<Price> prices;
	
	
	
}