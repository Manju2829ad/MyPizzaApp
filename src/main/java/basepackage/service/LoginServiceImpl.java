package basepackage.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import basepackage.dto.LoginDTO;
import basepackage.genericconverter.GenericConverter;
import basepackage.model.Login;
import basepackage.model.User;
import basepackage.repo.LoginRepository;
import basepackage.repo.UserRepository;


@EnableCaching
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LoginRepository loginRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final GenericConverter gc = new GenericConverter();

    
    
    
    @Override
    public User authenticateLogin(LoginDTO loginDTO) {

        System.out.println("Login Attempt: " + loginDTO);

        // Find user by email OR mobile number
        User user = userRepo.findByEmail(loginDTO.getUsername())
                .or(() -> userRepo.findByMobileNo(loginDTO.getUsername()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verify the password
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        // Log the login event
        Login login = new Login();
        login.setUser(user);
        login.setLoginTime(LocalDateTime.now());
        loginRepo.save(login);

        // Return the authenticated user entity
        return user;
    }

}
