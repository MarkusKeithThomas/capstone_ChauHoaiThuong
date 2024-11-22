package crm_app07buoi30.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07buoi30.service.LoginService;



@WebServlet(name = "loginController",urlPatterns = "/login")
public class LoginController extends HttpServlet{
	LoginService loginService = new LoginService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		String valueEmail = "";
		String valuePassword = "";
		boolean rememberme = false;
		String  signOut = req.getParameter("signOut");
		
		if ("signOut".equals(signOut)) {
	        // Thực hiện đăng xuất và chuyển hướng
	        loginService.getLogOut(resp);
	        resp.sendRedirect(req.getContextPath() + "/login"); // Chuyển hướng sau khi đăng xuất
	        return; // Chặn xử lý tiếp
	    }
		if (cookies != null) {		
			for(Cookie item : cookies) {
				String name = item.getName();
				String value = item.getValue();
				if(name.equals("email")) {
					valueEmail = value;
					rememberme = true;
				}
				if (name.equals("password")) {
					valuePassword = value;
				}
				req.setAttribute("email", valueEmail);
				req.setAttribute("password", valuePassword);
			}
			 }
		
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String  email = req.getParameter("email");
		String  password = req.getParameter("password");
		// Lấy giá trị checkbox "rememberme"
	    String rememberMe = req.getParameter("rememberme");
		
		boolean isCheck = loginService.findLogin(email, password);
		if(isCheck) {
			if ("true".equals(rememberMe)) {
				Cookie cookieEmail = new Cookie("email", email);
				Cookie cookiepassword = new Cookie("password",password);
				resp.addCookie(cookieEmail);
				resp.addCookie(cookiepassword);
                cookieEmail.setMaxAge(60 * 60 * 24 * 7); // Lưu cookie trong 7 ngày
                cookiepassword.setMaxAge(60 * 60 * 24 * 7); // Lưu cookie trong 7 ngày

			} else {
                Cookie cookiePass = new Cookie("password", null);
                cookiePass.setMaxAge(0); // Đặt thời gian sống bằng 0 để xóa cookie
                resp.addCookie(cookiePass);
			}
	        resp.sendRedirect(req.getContextPath() + "/user-table");
		} else {
            req.setAttribute("loginError", "Tài khoản hoặc mật khẩu không đúng.");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}

		
		

	}

	
}
