# Website Quản Lý Sinh Viên

## 1. Thông tin nhóm
- Thành viên 1: Hồ Đăng Khoa (Mã SV: 2305CT0517)
- Thành viên 2: Duy Nhân (Mã SV: 2305CT0523)
- Thành viên 3: [Điền tên] (Mã SV: ...)
- Thành viên 4: [Điền tên] (Mã SV: ...)
*(Lưu ý: Bạn tự điền lại tên và MSSV chuẩn xác của các thành viên trong nhóm vào đây nha)*

## 2. Mô tả đề tài
Website Quản lý Sinh viên hỗ trợ Giảng viên trong việc theo dõi, thêm, sửa, xóa dữ liệu sinh viên (điểm, học phí, chuyên ngành...) một cách trực quan và nhanh chóng. Hệ thống phân quyền rõ ràng, giúp sinh viên có thể đăng nhập tra cứu và cập nhật thông tin cá nhân một cách dễ dàng.

## 3. Công nghệ sử dụng
- Java Servlet
- JSP (JavaServer Pages)
- JDBC (Java Database Connectivity)
- HTML/CSS/JavaScript
- MySQL
- Apache Tomcat
- Trình quản lý thư viện: Maven

## 4. Các chức năng chính
- Đăng nhập có Phân quyền (Giảng viên và Sinh viên).
- Giảng viên (Admin): Toàn quyền xem, Thêm mới, Chỉnh sửa, và Xóa thông tin sinh viên.
- Sinh viên (User): Xem danh sách lớp, tự động cập nhật thông tin cá nhân (ngoại trừ Học phí).
- Chức năng lọc danh sách sinh viên theo Chuyên ngành (CNTT, QTKD, HTTT...).

## 5. Hướng dẫn cài đặt
1. Clone project từ GitHub về máy: `git clone https://github.com/dngkhoa231/doan-web-java.git`
2. Mở thư mục project bằng IDE (Visual Studio Code, Eclipse hoặc IntelliJ).
3. Mở phần mềm quản lý MySQL (VD: XAMPP - phpMyAdmin), import file `database.sql` để tạo CSDL `student_management`.
4. Nếu MySQL của bạn có cài mật khẩu, vui lòng cấu hình lại mật khẩu trong file `src/main/java/com/studentmvc/util/DBConnection.java`. (Mặc định tài khoản là `root`, mật khẩu để trống).
5. Chạy lệnh `mvn clean package` để build project.
6. Mở Apache Tomcat, triển khai project lên Server và truy cập đường dẫn `http://localhost:8080/student-management/`.

## 6. Tài khoản demo
- Admin (Giảng viên):
  - Tài khoản: `GV001`
  - Mật khẩu: `123`
- User (Sinh viên):
  - Tài khoản: `2305CT0517`
  - Mật khẩu: `123`

## 7. Video thuyết trình và demo
- [Bạn dán link YouTube quay video thuyết trình của nhóm vào đây nhé]
