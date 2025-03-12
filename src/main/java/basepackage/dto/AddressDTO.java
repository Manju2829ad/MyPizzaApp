package basepackage.dto;

import basepackage.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

	
	
	private Long  id;
	


	private String address;
	private String houseno;
	private String pincode;
	
	

	private User  user;
		
}
