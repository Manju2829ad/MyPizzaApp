package basepackage.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import basepackage.dto.LoginDTO;
import basepackage.genericconverter.GenericConverter;
import basepackage.model.Login;
import basepackage.model.User;
import basepackage.repo.LoginRepository;
import basepackage.repo.UserRepository;

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

        System.out.println("LoginDTO: " + loginDTO);

        // Convert LoginDTO to Login entity
        Login login = gc.convertToEntity(loginDTO, Login.class);

        // Fetch the user by mobile number (You can adapt to email logic if necessary)
        User user = userRepo.findByMobileNo(loginDTO.getUser().getMobileNo())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verify the password
        if (!passwordEncoder.matches(loginDTO.getUser().getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        // Log the login event
        login.setUser(user);
        login.setLoginTime(LocalDateTime.now());
        loginRepo.save(login);

        // Return the authenticated user entity (without token generation)
        return user;
    }
}
