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
                String studentCode = rs.getString("student_code");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                String major = rs.getString("major");
                double fee = rs.getDouble("fee");
                Student s = new Student(id, studentCode, name, email, dob, major, fee);
                s.setClassName(rs.getString("class_name"));
                students.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> selectAllStudentsByClass(String className) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE class_name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, className);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String studentCode = rs.getString("student_code");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date dob = rs.getDate("dob");
                    String major = rs.getString("major");
                    double fee = rs.getDouble("fee");
                    Student s = new Student(id, studentCode, name, email, dob, major, fee);
                    s.setClassName(rs.getString("class_name"));
                    students.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> selectAllStudentsByMajor(String majorCode) {
        List<Student> students = new ArrayList<>();
        // Truy vấn lọc theo chuyên ngành
        String sql = "SELECT * FROM students WHERE major = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, majorCode);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String studentCode = rs.getString("student_code");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date dob = rs.getDate("dob");
                    String major = rs.getString("major");
                    double fee = rs.getDouble("fee");
                    Student s = new Student(id, studentCode, name, email, dob, major, fee);
                    s.setClassName(rs.getString("class_name"));
                    students.add(s);
                }
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
                double fee = rs.getDouble("fee");
                student = new Student(id, studentCode, name, email, dob, major, fee);
                student.setClassName(rs.getString("class_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (student_code, name, email, dob, major, class_name, fee) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentCode());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setDate(4, student.getDob());
            pstmt.setString(5, student.getMajor());
            pstmt.setString(6, student.getClassName());
            pstmt.setDouble(7, student.getFee());
            pstmt.executeUpdate();
        }
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        String sql = "UPDATE students SET student_code = ?, name = ?, email = ?, dob = ?, major = ?, fee = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentCode());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setDate(4, student.getDob());
            pstmt.setString(5, student.getMajor());
            pstmt.setDouble(6, student.getFee());
            pstmt.setInt(7, student.getId());
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
