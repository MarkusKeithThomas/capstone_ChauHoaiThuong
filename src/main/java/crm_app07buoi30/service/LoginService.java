package crm_app07buoi30.service;

import crm_app07buoi30.config.Users;
import crm_app07buoi30.repository.LoginRepository;

public class LoginService {
	
	private LoginRepository loginRepository = new LoginRepository();
	
	public boolean findLogin(String email, String password) {
		Users users = loginRepository.findLogin(email, password);

		if (users.getEmail() != null && users.getPassword() != null) {
			
            System.out.println("true");
			return true;

		} else {
            System.out.println("false");
			return false;
		}
	}
}
