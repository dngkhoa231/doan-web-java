# Website Quản Lý Sinh Viên

## 1. Thông tin nhóm
- Thành viên 1: Hồ Đăng Khoa - 2305CT0517
- Thành viên 2: Duy Nhân - 2305CT0598
- Thành viên 3: Chống A Bối - 2305CT6473
- Thành viên 4: Nguyễn Hoài Khiêm - 2305CT0660
- Thành viên 5: Trần Đức Dương - 2305CT2042
- Thành viên 6: Vũ Viết Đạt - 2305CT8292

## 2. Mô tả đề tài
Website Quản lý Sinh viên hỗ trợ Giảng viên trong việc theo dõi, thêm, sửa, xóa dữ liệu sinh viên (điểm, học phí, chuyên ngành...) . Hệ thống phân quyền rõ ràng, giúp sinh viên có thể đăng nhập tra cứu và cập nhật thông tin cá nhân một cách dễ dàng.

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

## 5. Hướng dẫn cài đặt chi tiết (Dành cho Giảng viên chấm thi)

Để dự án có thể chạy trơn tru trên máy, Thầy vui lòng thực hiện theo các bước sau:

**Bước 1: Tải mã nguồn**
1. Clone project từ GitHub về máy: `git clone https://github.com/dngkhoa231/Website-Quan-Ly-Sinh-Vien.git`
2. Mở thư mục project bằng IDE (Visual Studio Code, Eclipse hoặc IntelliJ).
3. Mở phần mềm quản lý MySQL (VD: XAMPP - phpMyAdmin), import file `database/database.sql` để tạo CSDL `student_management`.
4. Nếu MySQL của bạn có cài mật khẩu, vui lòng cấu hình lại mật khẩu trong file `src/main/java/com/studentmvc/util/DBConnection.java`. (Mặc định tài khoản là `root`, mật khẩu để trống).
5. Chạy lệnh `mvn clean package` để build project.
6. **Cấu hình Server Tomcat và Chạy Project:**
   - **Lưu ý:** Project sử dụng `jakarta.servlet` nên **yêu cầu Apache Tomcat 10** trở lên.
   - Thêm Server Tomcat 10 vào IDE đang sử dụng (Eclipse, IntelliJ, hoặc VS Code qua extension *Community Server Connectors*).
   - Triển khai (Deploy) file `student-management.war` (nằm trong thư mục `target` sau khi build) lên Server.
   - Khởi động Server Tomcat và truy cập đường dẫn: `http://localhost:8080/student-management/`

[👉 Click vào đây để xem ảnh minh họa](anhminhhoa.png)

## 6. Tài khoản demo
- Admin (Giảng viên): Lê Duy Tân  ( ưu tiên dùng cái này nhé Thầy vì full chắc năng ạ  )
  - Tài khoản: `GV001`
  - Mật khẩu: `123`
- User (Sinh viên): ( chỉ xem và sửa thông tin của chính mình )
  - Tài khoản: `2305CT0517`
  - Mật khẩu: `123`

## 7. Video thuyết trình và demo
<video width="700" controls>
  <source src="./video/InShot_20260712_220942356.mp4" type="video/mp4">
</video>
