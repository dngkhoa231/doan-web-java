package com.studentmvc.model;

import java.sql.Date;

public class Student {
    private int id;
    private String studentCode; // Thêm Mã số sinh viên (MSSV)
    private String name;
    private String email;
    private Date dob;
    private String major;

    public Student() {}

    public Student(int id, String studentCode, String name, String email, Date dob, String major) {
        this.id = id;
        this.studentCode = studentCode;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.major = major;
    }

    public Student(String studentCode, String name, String email, Date dob, String major) {
        this.studentCode = studentCode;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.major = major;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}
