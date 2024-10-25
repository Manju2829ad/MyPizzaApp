package basepackage.service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import basepackage.dao.UserDao;
import basepackage.dto.AddressDTO;
import basepackage.dto.OrderDTO;
import basepackage.dto.UserDTO;
import basepackage.model.Address;
import basepackage.model.Order;
import basepackage.model.User;
import basepackage.repo.UserRepository;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 * Why Should You Convert Between DTO and Entity?
Avoid Circular References: DTOs prevent issues like circular references and infinite loops when dealing with bi-directional relationships.
Data Encapsulation: DTOs control the data exposed to clients, hiding internal implementation details.
Decoupling Presentation and Persistence: This separation makes it ea
 * 
 * 
 * 
 */

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Autowired
	private    UserRepository userRepo;
	

public UserDTO   userToDTO(User user) {
	
	//created a new userDTO object 
	UserDTO userDTO=new UserDTO();
	
	userDTO.setUid(user.getUid());
	userDTO.setFirstName(user.getFirstName());
	userDTO.setLastName(user.getLastName());
	userDTO.setMobileNo(user.getMobileNo());
	userDTO.setEmail(user.getEmail());
	userDTO.setPassword(user.getPassword());
	
	//Convert List<Address> to List<AddressDTO>

	
	List<AddressDTO>  address= user.getAddress()  .stream().map(this::convertAddressToDTO).collect(Collectors.toList());
	
	
	
	userDTO.setAddresses(address);
	
	
	return userDTO;
			
}
	

	
	private AddressDTO convertAddressToDTO(Address address) {
		AddressDTO addressDto = new AddressDTO();
		 addressDto.setId(address.getId());
		 addressDto.setAddress(address.getAddress());
	addressDto.setHouseno(address.getHouseno());
	addressDto.setPincode(address.getPincode());
	addressDto.setUser(address.getUser());
	return  addressDto;
		
		
	}
	
	
	//de-serialize
	public User convertToUser(UserDTO userDTO) {
		
		System.out.println("i am from convertToUser method");
		User user=new  User();
		user.setUid(userDTO.getUid());
		
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setMobileNo(userDTO.getMobileNo());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		
		
		//converting the  List<addressDTO > to List<Adderss> 
		List<Address> address=  userDTO.getAddresses() .stream().map(addressDTO -> convertDTOtoAddress(addressDTO, user)).collect(Collectors.toList());
	                 user.setAddress(address);
		
	                 System.out.println(user+":user details");
	                 
	                 
		return user;
	}
	
	
	
	private Address  convertDTOtoAddress(AddressDTO  addDTO ,User user) {
		
		Address address =new Address();
		address.setId(addDTO.getId());
		address.setAddress(addDTO.getAddress());
		address.setHouseno(addDTO.getHouseno());
		address.setPincode(addDTO.getPincode());
		
		
		System.out.println(addDTO.getUser()+"what is that ");
		address.setUser(user);
		
		System.out.println(address+":address");
		
		 return address;
		
	}
	
	
	
//	
//	@Override
//	public void saveUser(UserDTO userDTO) {
//	    log.info("Saving user: {}", userDTO);
//
//	    // Map UserDTO to User Entity
//	    User user = new User();
//	    user.setFirstName(userDTO.getFirstName());
//	    user.setLastName(userDTO.getLastName());
//	    user.setMobileNo(userDTO.getMobileNo());
//	    user.setEmail(userDTO.getEmail());
//
//	    // Map Addresses
//	    List<Address> addresses = new ArrayList<>();
//	    if (userDTO.getAddresses() != null) {
//	        for (AddressDTO addDTO : userDTO.getAddresses()) {
//	            Address addr = new Address();
//	            addr.setAddress(addDTO.getAddress());
//	            addr.setHouseno(addDTO.getHouseno());
//	            addr.setPincode(addDTO.getPincode());
//	            // Set id if necessary; if null, it indicates a new entity
////	            if (addDTO.getId() == null) {
////	   
////	                addr.setId(addDTO.getId());
////	                         	System.out.println(addDTO.getId()+"id");
////	            }
//	            
//	        
//	            addr.setUser(user);
//	            addresses.add(addr);
//	               user.setAddress(addresses);   
//	        }
//	  
//	    }
//
//	
//	    // Save or Merge User
//	    try {
//	    	System.out.println("hello");
//	    
//	        userRepo.save(user);
//	        
//	        	System.out.println(user+":------user");
//	        log.info("User saved successfully: {}", user);
//	    } catch (Exception e) {
//	        log.error("Error occurred while saving user: {}", e.getMessage(), e);
//	        throw e;  // Rethrow or handle exception as needed
//	    }
//	}


	@Override
	public UserDTO findById(Long id) {
		
		System.out.println("findbyid");
		User user=userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found with id:"+id));
		
		System.out.println(user+":user");
		  UserDTO dto=userToDTO(user);
		  
		  return dto;
	}
		


	@Autowired
	private PasswordEncoder passwordEncoder;

	public String saveUser(UserDTO userDTO) {
	    String msgOk = "ok";
	    String notOk = "notok";

	    User user = convertToUser(userDTO);

	    // Hash the password before saving
	    if (user.getPassword() != null) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	    }

	    try {
	        User saved = userRepo.save(user);
	        if (saved != null) {
	            return msgOk;
	        } else {
	            return notOk;
	        }
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        return notOk;
	    }
	}

	@Override
	public String updateUser(UserDTO userDTO) {
	    try {
	        // Retrieve the existing user by ID
	        User existingUser = userRepo.findById(userDTO.getUid())
	                .orElseThrow(() -> new RuntimeException("User not found with id: " + userDTO.getUid()));

	        // Update only non-null fields in the UserDTO
	        if (userDTO.getFirstName() != null && !userDTO.getFirstName().isEmpty()) {
	            existingUser.setFirstName(userDTO.getFirstName());
	        }
	        if (userDTO.getLastName() != null && !userDTO.getLastName().isEmpty()) {
	            existingUser.setLastName(userDTO.getLastName());
	        }
	        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
	            existingUser.setEmail(userDTO.getEmail());
	        }

	        // Save the updated user back to the database
	        userRepo.save(existingUser);
	        log.info("User details updated su"
	        		+ "ccessfully: {}", existingUser);
	        
	        return "ok";
	    } catch (HibernateException e) {
	        log.error("Error occurred while updating user details: {}", e.getMessage(), e);
	        return "notok";
	    }
	}


	
}