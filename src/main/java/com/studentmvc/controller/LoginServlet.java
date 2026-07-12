package com.studentmvc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.studentmvc.util.DBConnection;
import com.studentmvc.util.HashUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String rawPassword = request.getParameter("password");
        
        // Băm mật khẩu người dùng nhập vào để so với DB
        String password = HashUtil.hashPassword(rawPassword);

        System.out.println("=========================================");
        System.out.println("LOGIN REQUEST");
        System.out.println("Username : " + username);
        System.out.println("Password : " + rawPassword);
        System.out.println("=========================================");
        System.out.println("=========================================");
        System.out.println("Username       : " + username);
        System.out.println("Raw Password   : " + rawPassword);
        System.out.println("SHA256 Hash    : " + password);
        System.out.println("\nĐang thực hiện truy vấn...");

        try (Connection conn = DBConnection.getConnection()) {
            // Kiểm tra Giảng viên trước


            
            String sqlTeacher = "SELECT * FROM teachers WHERE teacher_code = ? AND password = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlTeacher)) {
                ps.setString(1, username);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println(">>> LOGIN SUCCESS <<<");
                        System.out.println(">>> LOGIN SUCCESS <<<");
                        System.out.println("Insert Result = true");
                        HttpSession session = request.getSession();
                        session.setAttribute("role", "TEACHER");
                        session.setAttribute("name", rs.getString("name"));
                        session.setAttribute("className", rs.getString("manage_class"));
                        response.sendRedirect("students");
                        return;
                    }
                }
            }
            
            // Nếu không phải GV  kiểm tra Sinh viên
            String sqlStudent = "SELECT * FROM students WHERE student_code = ? AND password = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlStudent)) {
                ps.setString(1, username);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println(">>> LOGIN SUCCESS <<<");
                        System.out.println(">>> LOGIN SUCCESS <<<");
                        System.out.println("Insert Result = true");
                        HttpSession session = request.getSession();
                        session.setAttribute("role", "STUDENT");
                        session.setAttribute("name", rs.getString("name"));
                        session.setAttribute("className", rs.getString("class_name"));
                        response.sendRedirect("students");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Nếu chạy đến đây là sai tài khoản hoặc pass
        request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
