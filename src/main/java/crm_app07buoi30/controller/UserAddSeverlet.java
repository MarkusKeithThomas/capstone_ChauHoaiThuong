package crm_app07buoi30.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07buoi30.config.Roles;
import crm_app07buoi30.service.ListUserService;
import crm_app07buoi30.service.RoleTableService;

@WebServlet(name = "userAddSeverlet",urlPatterns = "/user-add")
public class UserAddSeverlet extends HttpServlet{
	RoleTableService roleTableService = new RoleTableService();
	ListUserService listUserService = new ListUserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Roles> listRoles = roleTableService.getRoles();
		req.setAttribute("listroles", listRoles);
		// Chuyển tiếp đến JSP để hiển thị
        req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy giá trị từ các tham số trong form
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String roleString = req.getParameter("role");
        int roleInt = Integer.parseInt(roleString);
		if(listUserService.checkingFilling(email, password, fullname, phone)) {
			listUserService.insertMember(email, password, fullname, roleInt, phone);
			// Chuyển hướng tới trang danh sách người dùng
	        resp.sendRedirect(req.getContextPath() + "/user-table");
		} else {
			System.out.println("Cần điền đầy đủ thông tin.");
			doGet(req, resp);
			req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
		}
		
	}


}
