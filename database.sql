CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    dob DATE,
    major VARCHAR(100)
);

INSERT INTO students (student_code, name, email, dob, major) VALUES
('SV001', 'Nguyen Van A', 'nva@example.com', '2000-01-15', 'Cong Nghe Thong Tin'),
('SV002', 'Tran Thi B', 'ttb@example.com', '2001-03-22', 'He Thong Thong Tin');
