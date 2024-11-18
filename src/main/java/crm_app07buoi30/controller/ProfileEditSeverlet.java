package crm_app07buoi30.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app07buoi30.config.Status;
import crm_app07buoi30.config.TaskInfor;
import crm_app07buoi30.service.StatusTaskService;
import crm_app07buoi30.service.TaskInforService;

@WebServlet(name = "profileEditSeverlet", urlPatterns = "/profile-edit")
public class ProfileEditSeverlet extends HttpServlet {

    StatusTaskService statusTaskService = new StatusTaskService();
    TaskInforService taskInforService = new TaskInforService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTask = req.getParameter("idTask");
        if (idTask == null || idTask.isEmpty()) {
            // Chuyển hướng đến trang /profile nếu idTask không tồn tại
            resp.sendRedirect(req.getContextPath() + "/profile");
            return; // Kết thúc xử lý sau khi chuyển hướng
        }
        // Tiếp tục xử lý idTask
        TaskInfor taskInfor = taskInforService.getOneTaskById(Integer.parseInt(idTask));
        if (taskInfor != null) {
            req.setAttribute("taskInfor", taskInfor);
        } else {
            req.setAttribute("error", "Không tìm thấy công việc.");
        }
        // Gọi service để lấy danh sách trạng thái
        List<Status> status = statusTaskService.getAllStatus();
        // Đặt danh sách trạng thái vào request
        req.setAttribute("status", status);
        // Chuyển tiếp đến JSP để hiển thị
        req.getRequestDispatcher("/profile-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTask = req.getParameter("idTask");
        if (idTask != null && !idTask.isEmpty()) {
            try {
                // Lấy trạng thái từ request
                String nameStatusString = req.getParameter("status");
                int nameStatusIdInt = Integer.parseInt(nameStatusString);
                int idTaskInt = Integer.parseInt(idTask);

                // Cập nhật dữ liệu
                int rowsUpdated = taskInforService.updateOneTaskById(idTaskInt, nameStatusIdInt);

                // Kiểm tra kết quả cập nhật
                if (rowsUpdated > 0) {
                    System.out.println("Cập nhật thành công!");
                } else {
                    System.out.println("Không có bản ghi nào được cập nhật.");
                }
                // Chuyển hướng về trang profile
                resp.sendRedirect(req.getContextPath() + "/profile");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi định dạng dữ liệu: " + e.getMessage());
                req.setAttribute("error", "Dữ liệu đầu vào không hợp lệ.");
                doGet(req, resp); // Quay lại form chỉnh sửa
            }
        } else {
            System.out.println("ID Task không tồn tại.");
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
    }
}
