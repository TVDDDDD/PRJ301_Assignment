<%-- 
    Document   : create_leave
    Created on : Jul 18, 2025, 10:57:50 AM
    Author     : ACER
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tạo Đơn Nghỉ Phép</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Tạo Đơn Xin Nghỉ Phép</h2>
    <form action="create_leave" method="post" class="w-50">
        <div class="mb-3">
            <label for="startDate" class="form-label">Từ ngày</label>
            <input type="date" class="form-control" id="startDate" name="startDate" required>
        </div>
        <div class="mb-3">
            <label for="endDate" class="form-label">Tới ngày</label>
            <input type="date" class="form-control" id="endDate" name="endDate" required>
        </div>
        <div class="mb-3">
            <label for="reason" class="form-label">Lý do</label>
            <textarea class="form-control" id="reason" name="reason" rows="4" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Gửi đơn</button>
        <a href="list_leaves.jsp" class="btn btn-secondary ms-2">Quay lại</a>
    </form>
</div>
</body>
</html>
