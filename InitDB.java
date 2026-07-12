import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InitDB {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8&allowMultiQueries=true";
        String USER = "root";
        String PASSWORD = ""; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            
            String sql = new String(Files.readAllBytes(Paths.get("database.sql")), StandardCharsets.UTF_8);
            
            boolean isResultSet = stmt.execute(sql);
            while (true) {
                if (!isResultSet && stmt.getUpdateCount() == -1) {
                    break;
                }
                isResultSet = stmt.getMoreResults();
            }
            System.out.println("DATABASE CẬP NHẬT THÀNH CÔNG (Full UTF-8)!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
