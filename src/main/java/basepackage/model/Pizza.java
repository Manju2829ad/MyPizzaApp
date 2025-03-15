package basepackage.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private String image; // Updated to String for image URL or path
    private String description;
    private String sizes;
    private String crust;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isVeg;

    @JsonManagedReference(value = "toppings-pizza")  // Starts serialization for Toppings associated with Pizza
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topping> toppings;


    @JsonManagedReference(value = "prices-pizza")  // Starts serialization for Prices associated with Pizza
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<Price> prices;
    
    
    @Override
    public String toString() {
        return "Pizza{" +
               "id=" + id +
               ", name='" + name + '\'' +
               // Exclude price list or use a simplified format here
               '}';
    }
    
   
    public String getImage() {
        if (image != null) {
        	
        	if(image.startsWith("http")) {
        		
        		return image;
        	}
            return "/images/" + image;
        }
        return null;
    }
    
    
    
    
    
}
