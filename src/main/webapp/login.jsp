<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập hệ thống</title>
</head>
<body style="font-family: Arial;">

<center>
    <h2 style="color: blue;">ĐĂNG NHẬP</h2>
    
    <c:if test="${not empty error}">
        <p style="color: red;"><b>${error}</b></p>
    </c:if>

    <form action="login" method="post">
        <table border="1" cellpadding="10" cellspacing="0">
            <tr>
                <td><b>Tài khoản:</b></td>
                <td><input type="text" name="username" required></td>
            </tr>
            <tr>
                <td><b>Mật khẩu:</b></td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Đăng nhập" style="padding: 5px 15px;">
                </td>
            </tr>
        </table>
    </form>
    
    <p><i>Mật khẩu mặc định: 123</i></p>
</center>

</body>
</html>
