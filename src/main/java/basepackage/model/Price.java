package basepackage.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Price {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long pid;
	 private String size;
	 private String type;
	 
	 private Double price;
	 
	 
	 @JsonBackReference(value="prices-pizza")
	 @ManyToOne
	  private  Pizza pizza;
	 
	 
}