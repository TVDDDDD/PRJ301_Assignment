<%-- 
    Document   : list_leaves
    Created on : Jul 18, 2025, 10:59:37 AM
    Author     : ACER
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.leaveapp.model.Leave, java.util.List" %>
<html>
<head>
    <title>Danh Sách Đơn Nghỉ Phép</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Danh Sách Đơn Xin Nghỉ Phép</h2>
    <a href="create_leave.jsp" class="btn btn-primary mb-3">Tạo đơn mới</a>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Lý do</th>
                <th>Từ ngày</th>
                <th>Tới ngày</th>
                <th>Người tạo</th>
                <th>Trạng thái</th>
                <th>Người xử lý</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Leave> leaves = (List<Leave>) request.getAttribute("leaves");
                if (leaves != null) {
                    for (Leave leave : leaves) {
            %>
            <tr>
                <td><%= leave.getReason() %></td>
                <td><%= leave.getStartDate() %></td>
                <td><%= leave.getEndDate() %></td>
                <td><%= leave.getUserUsername() %></td>
                <td><%= leave.getStatus() %></td>
                <td><%= leave.getProcessedBy() != null ? leave.getProcessedBy() : "" %></td>
                <td>
                    <% 
                        com.leaveapp.model.User user = (com.leaveapp.model.User) session.getAttribute("user");
                        if (leave.getStatus().equals("Inprogress") && user != null && user.getRole().equals("Trưởng phòng")) { 
                    %>
                        <a href="process_leave.jsp?leaveId=<%= leave.getId() %>" class="btn btn-sm btn-warning">Xét duyệt</a>
                    <% } %>
                </td>
            </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
