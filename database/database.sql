DROP DATABASE IF EXISTS student_management;
CREATE DATABASE student_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE student_management;


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
    password VARCHAR(255) DEFAULT 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',
    CONSTRAINT fk_student_class FOREIGN KEY (class_name) REFERENCES classes(class_code) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    teacher_code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    manage_class VARCHAR(50),
    CONSTRAINT fk_teacher_class FOREIGN KEY (manage_class) REFERENCES classes(class_code) ON DELETE SET NULL ON UPDATE CASCADE
);


INSERT INTO classes (class_code, description) VALUES
('CT07', 'Lớp Công nghệ thông tin 07'),
('CT08', 'Lớp Công nghệ thông tin 08');

INSERT INTO teachers (teacher_code, name, password, manage_class) VALUES
('GV001', 'Lê Duy Tân', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'CT07'),
('GV002', 'Nguyễn Văn A', SHA2('admin@2024', 256), 'CT08');

INSERT INTO students (student_code, name, email, dob, major, class_name, fee, password) VALUES
('2305CT0517', 'Hồ Đăng Khoa ', 'dngkhoa231@gmail.com', '2002-02-22', 'CNTT', 'CT07', 30000, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('2305CT0523', 'Duy Nhân', 'duynhan@gmail.com', '2005-02-11', 'CNTT', 'CT07', 38000, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('2305CT0511', 'Bóng A chối ', 'bongbong@gmail.com', '2005-02-09', 'CNTT', 'CT07', 15000, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('2305CT0211', 'Khiêm nguyễn', '2305CT0211@gmail.com', '2005-09-09', 'QTKD', 'CT07', 45000, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('2305CT0521', 'Đức Dương ', 'k1s@gmail.com', '2003-09-09', 'HTTT', 'CT07', 15000, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('2305CT9992', 'Thanh Tâm lê Nguyễn', '2305CT9992@gmail.com', '2005-02-02', 'CNPM', 'CT07', 30000, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('2302CT9991', 'Minh Thức', '2302CT9991@gmail.com', '2005-02-09', 'QTKD', 'CT07', 15000, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
('2302CT9112', 'Lưu Minh Lạc', 'laklac231@gmail.com', '2004-07-20', 'CNPM', 'CT07', 12000, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
