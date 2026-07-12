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
        <div style="text-align: right; padding: 10px; background: #eee; margin-bottom: 20px;">
            Xin chào, <b>${sessionScope.name}</b> 
            <c:if test="${sessionScope.role == 'teacher'}">
                (Quản lý lớp: ${sessionScope.className})
            </c:if>
            <c:if test="${sessionScope.role != 'teacher'}">
                (Lớp: ${sessionScope.className})
            </c:if>
             | 
            <a href="logout">Đăng xuất</a>
        </div>
        
        <div class="header-action">
            <h2>Danh sách Sinh viên</h2>
            <a href="new" class="btn btn-primary">+ Thêm mới</a>
        </div>
        
        <!-- Form lọc sinh viên (chế thêm cho pro) -->
        <form action="students" method="GET" style="margin-bottom: 15px;">
            <b>Lọc theo Chuyên ngành: </b>
            <select name="major" onchange="this.form.submit()" style="padding: 4px;">
                <option value="">Tất cả</option>
                <option value="CNTT" <c:if test="${param.major == 'CNTT'}">selected</c:if>>CNTT</option>
                <option value="QTKD" <c:if test="${param.major == 'QTKD'}">selected</c:if>>QTKD</option>
                <option value="HTTT" <c:if test="${param.major == 'HTTT'}">selected</c:if>>HTTT</option>
            </select>
        </form>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã SV</th>
                    <th>Họ và Tên</th>
                    <th>Email</th>
                    <th>Ngày Sinh</th>
                    <th>Chuyên Ngành</th>
                    <th>Lớp</th>
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
                        <td><c:out value="${student.className}" /></td>
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
