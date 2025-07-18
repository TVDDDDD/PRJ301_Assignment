/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package servlet;

import dao.LeaveDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// Servlet for approving or rejecting leave requests
@WebServlet("/process_leave")
public class ProcessLeaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("Trưởng phòng")) {
            response.sendRedirect("login.jsp");
            return;
        }

        int leaveId = Integer.parseInt(request.getParameter("leaveId"));
        String action = request.getParameter("action");

        LeaveDAO dao = new LeaveDAO();
        dao.updateLeave(leaveId, action, user.getUsername());

        response.sendRedirect("list_leaves.jsp");
    }
}