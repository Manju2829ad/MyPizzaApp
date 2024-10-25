package basepackage.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import basepackage.model.Pizza;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {

	

	 private Long pid;
	 private String size;
	 private String type;
	 
	 private Double price;
	 
	 
	
	  private  Pizza pizza;
	 
}
