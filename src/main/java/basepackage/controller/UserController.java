package basepackage.controller;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

import basepackage.dto.UserDTO;
import basepackage.service.UserService;
import basepackage.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil; // Inject the JwtTokenUtil to use for token generation

    /**
     * Endpoint for creating a new user.
     * Generates a JWT token upon successful creation.
     */
    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        String successMessage = "User created successfully";
        String failureMessage = "User creation failed";

        try {
            // Save the user
            String result = userService.saveUser(userDTO);

            // If save is successful
            if ("ok".equalsIgnoreCase(result)) {
                // Generate a token using the user's email
                String token = jwtTokenUtil.generateTokenUsingEmail(userDTO.getEmail());
                return ResponseEntity.status(HttpStatus.CREATED).body(token);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failureMessage);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failureMessage);
        }
    }

    /**
     * Endpoint for fetching a user by ID with token validation.
     * Token is validated against email or mobile number.
     */
    @GetMapping(value = "/getuser/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        System.out.println("Inside the get method");

        System.out.println(token);
        // Remove "Bearer" from the token if it's passed with the prefix
        if (token.startsWith("Bearer")) {
            token = token.substring(7);
        }

        // Extract the subject (email or mobile number) from the token
        String subject = jwtTokenUtil.extractSubject(token);
        boolean isValidToken;

        // Validate token based on whether the subject is an email or mobile number
        if (subject.contains("@")) { // If the subject contains an "@" symbol, assume it's an email
            isValidToken = jwtTokenUtil.validateToken(token, subject);
        } else { // Otherwise, assume it's a mobile number
            System.out.println("Validating mobile number");
            isValidToken = jwtTokenUtil.validateToken(token, subject);
        }

        // If token validation fails, return a 401 Unauthorized
        if (!isValidToken) {
            System.out.println("Invalid token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        
        // Token is valid, proceed to fetch the user by ID
        UserDTO user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    

    // Add logger
  //  private static final Logger logger = LoggerFactory.getLogger(UserControllerClass.class);
    @PostMapping(value="/update", consumes = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO, @RequestHeader("Authorization") String token) {
        String success = "Details updated successfully";
        String failure = "Failed to update the details";

        try {
            // Check for "Bearer " prefix and remove it
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7); // Use 7 to include the space after "Bearer"
            }

            // Extract subject from token (e.g., email or mobile)
            String subject = jwtTokenUtil.extractSubject(token);
            
            // Validate the token
            boolean isValidToken = jwtTokenUtil.validateToken(token, subject);
            
            if (!isValidToken) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
            }

            // Proceed to update user details
            String response = userService.updateUser(userDTO);
            if ("ok".equalsIgnoreCase(response)) {
                return ResponseEntity.status(HttpStatus.OK).body(success);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failure);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while updating your details. Please try again later.");
        }
    }

    
    
    
    
}
