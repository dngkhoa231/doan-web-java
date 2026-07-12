<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Sinh Viên</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="container" style="max-width: 1250px;">
        
        <div class="user-info">
            <c:if test="${sessionScope.role == 'TEACHER'}">
                Xin chào, <b>Thầy ${sessionScope.name}</b>
            </c:if>
            <c:if test="${sessionScope.role == 'STUDENT'}">
                Xin chào, <b>${sessionScope.name}</b> (Lớp: ${sessionScope.className})
            </c:if>
            <a href="logout">Đăng xuất</a>
        </div>
        
        <div class="header-action">
            <h2>Danh sách Sinh viên</h2>
            <c:if test="${sessionScope.role == 'TEACHER'}">
                <a href="new" class="btn btn-primary">Thêm mới</a>
            </c:if>
        </div>
        
        <form action="students" method="GET" class="filter-form">
            <b>Lọc chuyên ngành: </b>
            <select name="major" onchange="this.form.submit()">
                <option value="">Tất cả</option>
                <option value="CNTT" <c:if test="${param.major == 'CNTT'}">selected</c:if>>CNTT</option>
                <option value="QTKD" <c:if test="${param.major == 'QTKD'}">selected</c:if>>QTKD</option>
                <option value="HTTT" <c:if test="${param.major == 'HTTT'}">selected</c:if>>HTTT</option>
            </select>
        </form>
        
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã SV</th>
                        <th>Họ và Tên</th>
                        <th>Email</th>
                        <th>Ngày Sinh</th>
                        <th>Chuyên Ngành</th>
                        <th>Học phí</th>
                        <th>Lớp</th>
                        <th style="text-align: center;">Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${listStudent}" varStatus="status">
                        <tr>
                            <td style="text-align: center;">${status.count}</td>
                            <td><b><c:out value="${student.studentCode}" /></b></td>
                            <td><c:out value="${student.name}" /></td>
                            <td><c:out value="${student.email}" /></td>
                            <td><c:out value="${student.dob}" /></td>
                            <td><c:out value="${student.major}" /></td>
                            <td><c:out value="${student.fee}" /></td>
                            <td><c:out value="${student.className}" /></td>
                            <td style="text-align: center;">
                                <a href="edit?id=<c:out value='${student.id}' />" class="btn btn-primary" style="padding: 6px 14px; font-size: 13px; border-radius: 8px;">Sửa</a>
                                &nbsp;
                                <c:if test="${sessionScope.role == 'TEACHER'}">
                                    <a href="delete?id=<c:out value='${student.id}' />" class="btn btn-danger" style="padding: 6px 14px; font-size: 13px; border-radius: 8px;" onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên này khỏi hệ thống?');">Xóa</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
