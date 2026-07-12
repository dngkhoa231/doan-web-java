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
        
        // Lấy cái đường dẫn người dùng gõ vào
        String action = request.getServletPath();
        
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertStudent(request, response);
                    break;
                case "/delete":
                    deleteStudent(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateStudent(request, response);
                    break;
                default:
                    listStudent(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Lọi hết sinh viên từ DB ra
        List<Student> listStudent = studentDAO.selectAllStudents();
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

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Lấy từng thông tin từ các ô input của form HTML
        String studentCode = request.getParameter("studentCode");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dobStr = request.getParameter("dob");
        Date dob = (dobStr != null && !dobStr.isEmpty()) ? Date.valueOf(dobStr) : null;
        String major = request.getParameter("major");
        
        Student newStudent = new Student(studentCode, name, email, dob, major);
        studentDAO.insertStudent(newStudent);
        response.sendRedirect("list");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String studentCode = request.getParameter("studentCode");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dobStr = request.getParameter("dob");
        Date dob = (dobStr != null && !dobStr.isEmpty()) ? Date.valueOf(dobStr) : null;
        String major = request.getParameter("major");

        Student student = new Student(id, studentCode, name, email, dob, major);
        studentDAO.updateStudent(student);
        response.sendRedirect("list");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        // Lấy mã ID để biết xóa đứa nào
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("list");
    }
}
