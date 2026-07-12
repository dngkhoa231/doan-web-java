<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập hệ thống</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<div class="container login-container">
    <h2>ĐĂNG NHẬP</h2>
    
    <c:if test="${not empty error}">
        <div class="error-msg">${error}</div>
    </c:if>

    <form action="login" method="post">
        <div class="form-group">
            <label for="username">Tài khoản:</label>
            <input type="text" id="username" name="username" placeholder="Nhập mã sinh viên hoặc giáo viên" required>
        </div>
        
        <div class="form-group">
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" placeholder="Nhập mật khẩu" required>
        </div>
        
        <button type="submit" class="btn btn-primary btn-block">Đăng nhập ngay</button>
    </form>
    
    <p style="text-align: center; margin-top: 25px; color: var(--text-muted); font-size: 13px;">
        Mật khẩu mặc định: <b>123</b>
    </p>
</div>

</body>
</html>
