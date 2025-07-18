<%-- 
    Document   : process_leave
    Created on : Jul 18, 2025, 11:00:50 AM
    Author     : ACER
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.leaveapp.dao.LeaveDAO, com.leaveapp.model.Leave" %>
<html>
<head>
    <title>Xét Duyệt Đơn Nghỉ Phép</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Xét Duyệt Đơn Xin Nghỉ Phép</h2>
    <% 
        int leaveId = Integer.parseInt(request.getParameter("leaveId"));
        LeaveDAO dao = new LeaveDAO();
        Leave leave = dao.getLeavesByUser(((com.leaveapp.model.User)session.getAttribute("user")).getUsername())
                        .stream().filter(l -> l.getId() == leaveId).findFirst().orElse(null);
        if (leave != null) {
    %>
    <form action="process_leave" method="post" class="w-50">
        <input type="hidden" name="leaveId" value="<%= leave.getId() %>">
        <div class="mb-3">
            <label class="form-label">Người tạo</label>
            <input type="text" class="form-control" value="<%= leave.getUserUsername() %>" disabled>
        </div>
        <div class="mb-3">
            <label class="form-label">Từ ngày</label>
            <input type="text" class="form-control" value="<%= leave.getStartDate() %>" disabled>
        </div>
        <div class="mb-3">
            <label class="form-label">Tới ngày</label>
            <input type="text" class="form-control" value="<%= leave.getEndDate() %>" disabled>
        </div>
        <div class="mb-3">
            <label class="form-label">Lý do</label>
            <textarea class="form-control" disabled><%= leave.getReason() %></textarea>
        </div>
        <button type="submit" name="action" value="Approved" class="btn btn-success">Duyệt</button>
        <button type="submit" name="action" value="Rejected" class="btn btn-danger ms-2">Từ chối</button>
        <a href="list_leaves.jsp" class="btn btn-secondary ms-2">Quay lại</a>
    </form>
    <% } else { %>
        <div class="alert alert-danger">Không tìm thấy đơn xin nghỉ phép!</div>
    <% } %>
</div>
</body>
</html>