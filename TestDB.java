import java.sql.*;

public class TestDB {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
        String USER = "root";
        String PASSWORD = ""; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
                try {
                    rs.getDate("dob");
                } catch (Exception e) {
                    System.out.println("DOB ERROR for " + rs.getString("name") + ": " + e.getMessage());
                }
            }
            System.out.println("Done reading.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
