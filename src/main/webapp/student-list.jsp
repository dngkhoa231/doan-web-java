<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Sinh Viên</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <div class="header-action">
            <h2>Sinh Viên</h2>
            <a href="new" class="btn btn-primary">+ Thêm mới</a>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã SV</th>
                    <th>Họ và Tên</th>
                    <th>Email</th>
                    <th>Ngày Sinh</th>
                    <th>Chuyên Ngành</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${listStudent}">
                    <tr>
                        <td><c:out value="${student.id}" /></td>
                        <td><c:out value="${student.studentCode}" /></td>
                        <td><c:out value="${student.name}" /></td>
                        <td><c:out value="${student.email}" /></td>
                        <td><c:out value="${student.dob}" /></td>
                        <td><c:out value="${student.major}" /></td>
                        <td>
                            <a href="edit?id=<c:out value='${student.id}' />" class="btn btn-primary">Sửa</a>
                            &nbsp;
                            <a href="delete?id=<c:out value='${student.id}' />" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
