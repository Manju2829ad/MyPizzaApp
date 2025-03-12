package basepackage.service;

import basepackage.dto.AddressDTO;
import basepackage.dto.UserDTO;
import basepackage.model.Address;
import basepackage.model.User;



public interface UserService {
	public String   saveUser(UserDTO userDTO);
	public UserDTO  userToDTO(User user);

	
	public  UserDTO findById(Long id);
	
	public String updateUser(UserDTO userDTO);
        
        
}