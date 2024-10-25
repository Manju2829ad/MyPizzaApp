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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
        try {
            // Validate request
            if (loginDTO.getUser() == null || loginDTO.getUser().getMobileNo() == null || loginDTO.getUser().getPassword() == null) {
                return ResponseEntity.badRequest().body("Mobile number and password are required.");
            }

            // Authenticate user through the service layer
            User user = loginService.authenticateLogin(loginDTO);

            // Generate JWT token
            String identifier = user.getMobileNo(); // or user.getEmail() based on your logic
            String token;
            if (identifier.contains("@")) {
                token = jwtTokenUtil.generateTokenUsingEmail(identifier);  // If email
            } else {
                token = jwtTokenUtil.generateTokenUsingMobile(identifier);  // If mobile number
            }

            // Prepare response data (Token + User Info)
            Map<String, Object> responseData = new HashMap();
            responseData.put("token", token);
            responseData.put("userId", user.getUid());  // Assuming `userId` is the primary key
          //  responseData.put("name", user.getFirstName() + " " + user.getLastName());  // Optional: additional user info
            //responseData.put("mobileNo", user.getMobileNo());  // Optional: additional user info

            // Return the response
            return ResponseEntity.ok().body(responseData);

        } catch (RuntimeException e) {
            // Handle any authentication failures
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
