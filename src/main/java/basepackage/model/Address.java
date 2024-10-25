package basepackage.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	
	private String address;
	private String houseno;
	private String pincode;
	


	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.ALL})
   // @JsonBackReference // Back reference
	@JoinColumn(name="cust_user_id")
	private User user;
	
	
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", houseno='" + houseno + '\'' +
                ", pincode='" + pincode + '\'' +
                // Optionally, you can include user ID if needed
                ", userId=" + (user != null ? user.getUid() : null) +
                '}';
    }
	
	
	
	
		
}