package basepackage.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long uid;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String email;
	private String password;
	
	   // Additional fields for address and orders
    private List<AddressDTO> addresses;  // List of AddressDTOs
//    private List<OrderDTO> orders;       // List of OrderDTOs
}
