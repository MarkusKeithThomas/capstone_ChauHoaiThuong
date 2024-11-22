package crm_app07buoi30.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
	public boolean getLogOut( HttpServletResponse resp) {
	    boolean resultSignOut = false;

	    try {
	        // Xóa cookie email và password
	        Cookie cookieEmail = new Cookie("email", null);
	        cookieEmail.setMaxAge(0); // Xóa ngay lập tức
	        cookieEmail.setPath("/");

	        Cookie cookiePassword = new Cookie("password", null);
	        cookiePassword.setMaxAge(0); // Xóa ngay lập tức
	        cookiePassword.setPath("/");

	        resp.addCookie(cookieEmail);
	        resp.addCookie(cookiePassword);


	        resultSignOut = true; // Đăng xuất thành công
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return resultSignOut;
	}


}
