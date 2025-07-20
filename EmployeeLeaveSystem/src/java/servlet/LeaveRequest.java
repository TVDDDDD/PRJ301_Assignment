/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package servlet;

import java.io.IOException;
   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.PreparedStatement;
   import jakarta.servlet.ServletException;
   import jakarta.servlet.annotation.WebServlet;
   import jakarta.servlet.http.HttpServlet;
   import jakarta.servlet.http.HttpServletRequest;
   import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

   @WebServlet("/LeaveRequest")
   public class LeaveRequest extends HttpServlet {
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
           if (!"manager".equals(request.getSession().getAttribute("role"))) {
               response.sendRedirect("unauthorized.jsp");
               return;
           }

           int userId = (int) request.getSession().getAttribute("userId");
           String startDate = request.getParameter("startDate");
           String endDate = request.getParameter("endDate");
           String reason = request.getParameter("reason");

           try {
               String url = "jdbc:sqlserver://localhost:1433;databaseName=LeaveManagementDB;encrypt=true;trustServerCertificate=true";
               try (Connection conn = DriverManager.getConnection(url, "sa", "your_password") // Thay your_password
               ) {
                   String sql = "INSERT INTO LeaveRequests (user_id, start_date, end_date, reason, status) VALUES (?, ?, ?, ?, 'inprogress')";
                   try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                       stmt.setInt(1, userId);
                       stmt.setDate(2, java.sql.Date.valueOf(startDate));
                       stmt.setDate(3, java.sql.Date.valueOf(endDate));
                       stmt.setString(4, reason);
                       stmt.executeUpdate();
                       
                       request.setAttribute("message", "Leave request submitted successfully");
                       request.getRequestDispatcher("/leaveRequest.jsp").forward(request, response);
                   }
               }
           } catch (ServletException | IOException | SQLException e) {
               request.setAttribute("error", "Database error");
               request.getRequestDispatcher("/leaveRequest.jsp").forward(request, response);
           }
       }
   }