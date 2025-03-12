package basepackage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import basepackage.dto.LoginDTO;
import basepackage.model.User;
import basepackage.service.LoginService;
import basepackage.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    
    private JwtTokenUtil jwtTokenUtil;


    public LoginController(){

        this.jwtTokenUtil=new JwtTokenUtil();
    }

    
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
        System.out.println("Login request: " + loginDTO);

        
        try {
            // Validate request
            if (loginDTO.getUsername() == null || loginDTO.getPassword() == null) {
                return ResponseEntity.badRequest().body("Username (Email or Mobile) and Password are required.");
            }

            // Authenticate user
            User user = loginService.authenticateLogin(loginDTO);

            // Generate JWT token using email or mobile
            String identifier = loginDTO.getUsername(); // Already email or mobile from request
            String token;
            if (identifier.contains("@")) {
                token = jwtTokenUtil.generateTokenUsingEmail(identifier);  // If email
            } else {
                token = jwtTokenUtil.generateTokenUsingMobile(identifier);  // If mobile number
            }

            // Prepare response data
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", token);
            responseData.put("userId", user.getUid());  // User ID
            responseData.put("name", user.getFirstName() + " " + user.getLastName()); // Optional: Full Name
            responseData.put("mobileNo", user.getMobileNo());  // Optional: Mobile Number

            // Return the response
            return ResponseEntity.ok().body(responseData);

        } catch (RuntimeException e) {
            // Handle authentication failures
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
