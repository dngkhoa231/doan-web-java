package com.studentmvc.controller;

import com.studentmvc.dao.StudentDAO;
import com.studentmvc.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

// Servlet này đóng vai trò làm Controller để điều hướng các thao tác Thêm, Sửa, Xóa
@WebServlet({"/students", "/new", "/insert", "/delete", "/edit", "/update", "/list"})
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set tiếng Việt để tránh bị lỗi font chữ
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Kiểm tra xem đã đăng nhập chưa
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        String className = (String) session.getAttribute("className");
        
        if (role == null) {
            response.sendRedirect("login");
            return;
        }

        // Lấy cái đường dẫn người dùng gõ vào
        String action = request.getServletPath();
        
        // Phân quyền: Ngăn sinh viên Thêm mới hoặc Xóa
        if ("STUDENT".equals(role) && (action.equals("/new") || action.equals("/insert") || action.equals("/delete"))) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<script>alert('Lỗi: Sinh viên không được phép Thêm mới hoặc Xóa!'); window.location.href='students';</script>");
            return;
        }
        
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertStudent(request, response, className);
                    break;
                case "/delete":
                    deleteStudent(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateStudent(request, response, className);
                    break;
                default:
                    listStudent(request, response, className);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response, String className)
            throws SQLException, IOException, ServletException {
        String major = request.getParameter("major");
        List<Student> listStudent;
        
        if (major != null && !major.isEmpty()) {
            listStudent = studentDAO.selectAllStudentsByMajor(major);
        } else {
            // Lấy TOÀN BỘ sinh viên (như web cũ)
            listStudent = studentDAO.selectAllStudents();
        }
        
        request.setAttribute("listStudent", listStudent);
        // Đẩy dữ liệu sang trang JSP để hiển thị
        request.getRequestDispatcher("student-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("student-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.selectStudent(id);
        request.setAttribute("student", existingStudent);
        request.getRequestDispatcher("student-form.jsp").forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response, String className)
            throws SQLException, IOException {
        String studentCode = request.getParameter("studentCode");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dobStr = request.getParameter("dob");
        Date dob = (dobStr != null && !dobStr.isEmpty()) ? Date.valueOf(dobStr) : null;
        String major = request.getParameter("major");
        String feeStr = request.getParameter("fee");
        double fee = (feeStr != null && !feeStr.isEmpty()) ? Double.parseDouble(feeStr) : 0;
        
        Student newStudent = new Student(studentCode, name, email, dob, major, fee);
        // Tự động gán lớp cho sinh viên này bằng lớp hiện tại
        newStudent.setClassName(className);
        
        try {
            studentDAO.insertStudent(newStudent);
            response.sendRedirect("students");
        } catch (SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println("<script>alert('Lỗi: Mã sinh viên hoặc Email đã tồn tại!'); history.back();</script>");
            } else {
                throw e;
            }
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response, String className)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String studentCode = request.getParameter("studentCode");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dobStr = request.getParameter("dob");
        Date dob = (dobStr != null && !dobStr.isEmpty()) ? Date.valueOf(dobStr) : null;
        String major = request.getParameter("major");
        String feeStr = request.getParameter("fee");
        double fee = (feeStr != null && !feeStr.isEmpty()) ? Double.parseDouble(feeStr) : 0;

        Student student = new Student(id, studentCode, name, email, dob, major, fee);
        student.setClassName(className);
        try {
            studentDAO.updateStudent(student);
            response.sendRedirect("students");
        } catch (SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry")) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println("<script>alert('Lỗi: Mã sinh viên hoặc Email đã tồn tại!'); history.back();</script>");
            } else {
                throw e;
            }
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("students");
    }
}
