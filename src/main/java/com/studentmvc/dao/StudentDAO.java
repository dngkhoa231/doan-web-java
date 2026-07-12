package com.studentmvc.dao;

import com.studentmvc.model.Student;
import com.studentmvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> selectAllStudents() {
        // Tạo cái danh sách rỗng để hứng dữ liệu
        List<Student> students = new ArrayList<>();
        // Câu lệnh SQL lấy tất cả sinh viên từ bảng
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String studentCode = rs.getString("student_code");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                String major = rs.getString("major");
                students.add(new Student(id, studentCode, name, email, dob, major));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student selectStudent(int id) {
        Student student = null;
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String studentCode = rs.getString("student_code");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                String major = rs.getString("major");
                student = new Student(id, studentCode, name, email, dob, major);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void insertStudent(Student student) throws SQLException {
        // Câu lệnh chèn sinh viên mới vào Database có thêm MSSV
        String sql = "INSERT INTO students (student_code, name, email, dob, major) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentCode());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setDate(4, student.getDob());
            pstmt.setString(5, student.getMajor());
            pstmt.executeUpdate();
        }
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        // Cập nhật lại thông tin đứa sinh viên này dựa vào ID
        String sql = "UPDATE students SET student_code = ?, name = ?, email = ?, dob = ?, major = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentCode());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setDate(4, student.getDob());
            pstmt.setString(5, student.getMajor());
            pstmt.setInt(6, student.getId());
            rowUpdated = pstmt.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        // Lệnh xóa sinh viên dứt điểm
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            rowDeleted = pstmt.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
