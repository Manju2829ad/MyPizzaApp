package basepackage.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cust_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uid")

public class User {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	
	
	private String  firstName;
	private String lastName;
	private String mobileNo;
	private String email;
	
	private String password;
	
//	@JsonManagedReference(value="orders-users")
//	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
//	private List<Order> orders;
//	
	
	//@JsonManagedReference
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Address> address;
	
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNo=" + mobileNo +
                ", email='" + email + '\'' +
                ", email='" + password+ '\'' +
                // Avoid printing addresses to prevent recursion
                '}';
    }
    
    
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Login> login;
    
    
    
    
}