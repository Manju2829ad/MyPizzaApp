package basepackage.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import basepackage.dto.UserDTO;
import basepackage.model.Address;
import basepackage.model.User;
import basepackage.repo.UserRepository;
import basepackage.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class) // Initialize Mockito
public class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser1;
    private User testUser2;

    @BeforeEach
    void setUp() {
        testUser1 = new User();
        
        Address address= new Address();
        
        address.setAddress("Bangalroe");
        
        testUser1.setUid(1L);
        testUser1.setFirstName("John");
        testUser1.setLastName("Doe");
        testUser1.setEmail("gg@gmail.com");
        testUser1.setMobileNo("8798788558");
        testUser1.setAddresses(List.of(address));

        testUser2 = new User();
        testUser2.setUid(2L);
        testUser2.setFirstName("Jane");
        testUser2.setLastName("Smith");
        testUser2.setEmail("jane@example.com");
        testUser2.setMobileNo("9876543210");
        testUser2.setAddresses(List.of(address));
    }

    // ✅ Test 1: User exists by email
    @Test
    void testFindIfUserExistsByEmail() {
        when(userRepo.findByEmail("gg@gmail.com")).thenReturn(Optional.of(testUser1));

        boolean result = userService.verifyIfUserExist("gg@gmail.com", "134567890");

        assertTrue(result); // ✅ Should return true since email exists
        verify(userRepo, times(1)).findByEmail("gg@gmail.com"); // Ensure repo is called
    }

    // ✅ Test 2: User exists by mobile number
    @Test
    void testUserExistsByMobileNumber() {
        when(userRepo.findByMobileNo("8798788558")).thenReturn(Optional.of(testUser1));

        boolean result = userService.verifyIfUserExist("", "8798788558");

        assertFalse(result); // ✅ Should return true since mobile exists
        verify(userRepo, times(1)).findByMobileNo("8798788558");
    }

    // ✅ Test 3: User exists by both email & mobile number
    @Test
    void testUserExistByMobileAndEmail() {
        when(userRepo.findByEmail("gg@gmail.com")).thenReturn(Optional.of(testUser1));
        when(userRepo.findByMobileNo("8798788558")).thenReturn(Optional.of(testUser1));

        boolean result = userService.verifyIfUserExist("gg@gmail.com", "8798788558");

        assertTrue(result); // ✅ Should return true since both exist
        verify(userRepo, times(1)).findByEmail("gg@gmail.com");
        verify(userRepo, times(1)).findByMobileNo("8798788558");
    }

    // ✅ Test 4: User does NOT exist
    @Test
    void testUserDoesNotExist() {
        when(userRepo.findByEmail("notfound@example.com")).thenReturn(Optional.empty());
        when(userRepo.findByMobileNo("0000000000")).thenReturn(Optional.empty());

        boolean result = userService.verifyIfUserExist("notfound@example.com", "0000000000");

        assertFalse(result); // ✅ Should return false since neither exist
        verify(userRepo, times(1)).findByEmail("notfound@example.com");
        verify(userRepo, times(1)).findByMobileNo("0000000000");
    }

    // ✅ Test 5: Find user by ID
    @Test
    void testFindById_UserExists() {
        when(userRepo.findById(1L)).thenReturn(Optional.of(testUser1));

        UserDTO result = userService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUid());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("gg@gmail.com", result.getEmail());
        assertEquals("8798788558", result.getMobileNo());

        verify(userRepo, times(1)).findById(1L);
    }
}
