package basepackage.service;

import basepackage.dto.LoginDTO;
import basepackage.model.Login;
import basepackage.model.User;

public interface LoginService {

	
	
    public User authenticateLogin(LoginDTO loginDTO);
}
