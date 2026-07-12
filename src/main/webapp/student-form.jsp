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
</head>
<body>
    <div class="container">
        <h2>
            <c:if test="${student != null}">Sửa thông tin</c:if>
            <c:if test="${student == null}">Thêm Sinh viên</c:if>
        </h2>

        <form action="<c:if test='${student != null}'>update</c:if><c:if test='${student == null}'>insert</c:if>" method="post">
            
            <c:if test="${student != null}">
                <input type="hidden" name="id" value="<c:out value='${student.id}' />" />
            </c:if>

            <div class="form-group">
                <label for="studentCode">Mã số sinh viên (MSSV):</label>
                <input type="text" id="studentCode" name="studentCode" value="<c:out value='${student.studentCode}' />" required>
            </div>

            <div class="form-group">
                <label for="name">Họ và Tên:</label>
                <input type="text" id="name" name="name" value="<c:out value='${student.name}' />" required>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<c:out value='${student.email}' />" required>
            </div>

            <div class="form-group">
                <label for="dob">Ngày Sinh:</label>
                <input type="date" id="dob" name="dob" value="<c:out value='${student.dob}' />">
            </div>

            <div class="form-group">
                <label for="major">Chuyên Ngành:</label>
                <input type="text" id="major" name="major" value="<c:out value='${student.major}' />">
            </div>

            <div style="margin-top: 2rem;">
                <button type="submit" class="btn btn-primary">Lưu thông tin</button>
                <a href="list" class="btn btn-danger" style="margin-left: 10px;">Hủy bỏ</a>
            </div>
        </form>
    </div>
</body>
</html>
