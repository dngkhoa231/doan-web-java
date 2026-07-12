package com.studentmvc.dao;

import com.studentmvc.model.Student;
import com.studentmvc.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                String major = rs.getString("major");
                students.add(new Student(id, name, email, dob, major));
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
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                String major = rs.getString("major");
                student = new Student(id, name, email, dob, major);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, email, dob, major) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setDate(3, student.getDob());
            pstmt.setString(4, student.getMajor());
            pstmt.executeUpdate();
        }
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        String sql = "UPDATE students SET name = ?, email = ?, dob = ?, major = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setDate(3, student.getDob());
            pstmt.setString(4, student.getMajor());
            pstmt.setInt(5, student.getId());
            rowUpdated = pstmt.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            rowDeleted = pstmt.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
