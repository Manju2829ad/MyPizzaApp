package basepackage.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import basepackage.dto.UserDTO;
import basepackage.model.User;
import basepackage.repo.UserRepository;
import basepackage.service.UserServiceImpl;

public class UserServiceTest {

	
	 @Mock
	private UserRepository userRepo;
	
	
	 
	 @InjectMocks
	 private UserServiceImpl  userService;
	 
	 
	 private User  testUser1;
	 
	 private User testUser2;
	 
	 
	 
	 @BeforeEach
	
	  void setUp() {
		 
		 testUser1= new User();
		 
		 testUser1.setUid(1L);
		 testUser1.setFirstName("John");
		 testUser1.setLastName("Doe");
		 testUser1.setEmail("gg@gmail.com");
		 
		 testUser1.setMobileNo("8798788558");
	
		  
		 testUser2= new User();
		 
		 testUser2.setUid(2L);
		 testUser2.setFirstName("John");
		 testUser2.setLastName("Doe");
		 testUser2.setEmail("gg@gmail.com");
		 
		 testUser1.setMobileNo("87987885523");
		 
	 }
	 
	      
	 
	 // Test 1 : User exists by email 
	 
	 @Test
	 void testFindIfUser_exists_ByMail() {
		 
	
		  
		  when(userRepo.findByEmail("gg@gmail.com")).thenReturn(Optional.of(testUser1));
		  
		  when(userRepo.findByEmail("")).thenReturn(Optional.empty());
		  
		  
		  boolean result = userService.verifyIfUserExist("gg@gmail.com", "134567890");

         assertTrue(result);
		  
	 }
	 
	 
	 
	 @Test
	 void testUserExistsByMobileNumber() {
		 
		 
		 when(userRepo.findByMobileNo("8798788557")).thenReturn(Optional.of(testUser1));
		 
		 when(userRepo.findByMobileNo("")).thenReturn(Optional.empty());
		 
		 
		 boolean result = userService.verifyIfUserExist("", "8798788557");
		 
		 assertFalse(result);
	 }
	 
	   
	 @Test
	 
	 void testUserExistByMobileandEmail() {
		 
	 when(userRepo.findByMobileNo("8798788557")).thenReturn(Optional.of(testUser1));
		 
		 when(userRepo.findByMobileNo("gg@gmail.com")).thenReturn(Optional.empty());
		 
		 
		 boolean result = userService.verifyIfUserExist("8798788557", "gg@gmail.com");
		 
		 
	 }
	 
	 
	 
	 @Test
	       void testFindById_UserExists() {
		 
		 //Mock repostiroy behavior 
		 
		 when(userRepo.findById(1L)).thenReturn(Optional.of(testUser1));
		 
		       
		 
		 // calling the acutal method 
		 
		 UserDTO result = userService.findById(1L);
		 // assertions 
		 
		 // verfiy if the reslt is not null
		 assertNotNull(result);
		 
		 //
		 
		 assertEquals(1L, result.getUid());
		 assertEquals("John",result.getFirstName());
		 assertEquals("Doe", result.getLastName());
		 
		 assertEquals("gg@gmail.com", result.getEmail());
		 
		 assertEquals("8798788558", result.getMobileNo());
		 
		
		 
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
}
