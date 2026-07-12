<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <c:if test="${student != null}">Cập nhật Sinh viên</c:if>
        <c:if test="${student == null}">Thêm Sinh viên mới</c:if>
    </title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="container" style="max-width: 650px;">
        <h2>
            <c:if test="${student != null}">Cập nhật thông tin</c:if>
            <c:if test="${student == null}">Thêm Sinh viên mới</c:if>
        </h2>

        <form action="<c:if test='${student != null}'>update</c:if><c:if test='${student == null}'>insert</c:if>" method="post" style="margin-top: 35px;">
            
            <c:if test="${student != null}">
                <input type="hidden" name="id" value="<c:out value='${student.id}' />" />
            </c:if>

            <div class="form-group">
                <label for="studentCode">Mã số sinh viên (MSSV):</label>
                <input type="text" id="studentCode" name="studentCode" value="<c:out value='${student.studentCode}' />" placeholder="VD: 2305CT0517" required>
            </div>

            <div class="form-group">
                <label for="name">Họ và Tên:</label>
                <input type="text" id="name" name="name" value="<c:out value='${student.name}' />" placeholder="Nhập họ và tên đầy đủ" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<c:out value='${student.email}' />" placeholder="Ví dụ: email@gmail.com" required>
            </div>

            <div class="form-group">
                <label for="dob">Ngày Sinh:</label>
                <input type="date" id="dob" name="dob" value="<c:out value='${student.dob}' />">
            </div>

            <div class="form-group">
                <label for="major">Chuyên Ngành:</label>
                <input type="text" id="major" name="major" value="<c:out value='${student.major}' />" placeholder="Ví dụ: CNTT, QTKD...">
            </div>

            <div class="form-group">
                <label for="fee">Học phí:</label>
                <input type="number" step="0.01" id="fee" name="fee" value="<c:out value='${student.fee}' />" placeholder="Nhập số tiền học phí">
            </div>

            <div style="margin-top: 40px; display: flex; gap: 20px;">
                <button type="submit" class="btn btn-primary" style="flex: 1;">Xác nhận lưu</button>
                <a href="students" class="btn btn-danger" style="flex: 1; text-align: center;">Hủy bỏ</a>
            </div>
        </form>
    </div>
</body>
</html>
