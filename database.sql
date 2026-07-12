DROP DATABASE IF EXISTS student_management;
CREATE DATABASE student_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE student_management;


CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    dob DATE,
    major VARCHAR(100),
    class_name VARCHAR(50),
    password VARCHAR(255) DEFAULT 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3' -- Mật khẩu mặc định 123 (mã băm)
);


CREATE TABLE teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    teacher_code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    manage_class VARCHAR(50) -- Lớp quản lý
);


INSERT INTO teachers (teacher_code, name, password, manage_class) VALUES
('GV001', 'Nguyễn Duy Tân', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'CT07'),

INSERT INTO students (student_code, name, email, dob, major, class_name, password) VALUES
('2305CT0517', 'Hồ Đăng Khoa', 'dngkhoa231@gmail.com', '2005-01-23', 'CNTT', 'CT07', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),


