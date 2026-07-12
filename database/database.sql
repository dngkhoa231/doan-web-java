DROP DATABASE IF EXISTS student_management;
CREATE DATABASE student_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE student_management;

-- Bảng thứ 3: Lớp học
CREATE TABLE classes (
    class_code VARCHAR(50) PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    dob DATE,
    major VARCHAR(100),
    class_name VARCHAR(50),
    fee DOUBLE DEFAULT 0,
    password VARCHAR(255) DEFAULT 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', -- Mật khẩu mặc định 123 (mã băm)
    CONSTRAINT fk_student_class FOREIGN KEY (class_name) REFERENCES classes(class_code) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    teacher_code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    manage_class VARCHAR(50), -- Lớp quản lý
    CONSTRAINT fk_teacher_class FOREIGN KEY (manage_class) REFERENCES classes(class_code) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Thêm dữ liệu mẫu bảng classes trước
INSERT INTO classes (class_code, description) VALUES
('CT07', 'Lớp Công nghệ thông tin 07'),
('CT08', 'Lớp Công nghệ thông tin 08');

INSERT INTO teachers (teacher_code, name, password, manage_class) VALUES
('GV001', 'Lê Duy Tân', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'CT07'),
('GV002', 'Nguyễn Văn A', SHA2('admin@2024', 256), 'CT08');

INSERT INTO students (student_code, name, email, dob, major, class_name, password) VALUES
('2305CT0517', 'Hồ Đăng Khoa', 'dngkhoa231@gmail.com', '2005-01-23', 'CNTT', 'CT07', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
