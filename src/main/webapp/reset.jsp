<%@ page import="java.sql.*, com.studentmvc.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Reset Pass</title></head>
<body>
<%
    String pwd = HashUtil.hashPassword("123");
    try (Connection conn = DBConnection.getConnection()) {
        // Fix for teacher
        PreparedStatement ps1 = conn.prepareStatement("UPDATE teachers SET password = ?");
        ps1.setString(1, pwd);
        int rows1 = ps1.executeUpdate();
        
        // Fix for student
        PreparedStatement ps2 = conn.prepareStatement("UPDATE students SET password = ?");
        ps2.setString(1, pwd);
        int rows2 = ps2.executeUpdate();
        
        out.println("<h2>Đã reset toàn bộ mật khẩu thành công!</h2>");
        out.println("<p>Bây giờ mật khẩu của tài khoản giảng viên <b>GV001</b> và sinh viên <b>2305CT0517</b> đều là: <b>123</b></p>");
        out.println("<a href='login' style='padding:10px; background: blue; color: white; text-decoration: none;'>Bấm vào đây để Đăng nhập lại</a>");
    } catch(Exception e) {
        out.println("<h2 style='color:red;'>Lỗi CSDL: " + e.getMessage() + "</h2>");
    }
%>
</body>
</html>
