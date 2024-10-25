package basepackage.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
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
@Entity
public class Pizza {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	

    private String name;
	

    private String category;
	

    private String image;  // Updated from Byte to String
	

    private String description;
	

         // Mark size as an embeddable collection
	    private  String sizes;
	

          // Mark crust as an embeddable collection
	    private  String  crust;

	    
	    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isVeg;  // Changed to Boolean for better representation

    @JsonManagedReference(value = "toppings-pizza")
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<Topping> toppings;

    @JsonManagedReference(value = "prices-pizza")
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<Price> prices;
}
