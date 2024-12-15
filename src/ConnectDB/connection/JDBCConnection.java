/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDB.connection;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author abc
 */
public class JDBCConnection {
    public static Connection getJDBCConnection(){
        final String url = "jdbc:mysql://localhost:3306/prj13";
        final String user = "root";
        final String password = "123456";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        }catch  (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
//    public static void main(String[] args){
//        Connection connection = getJDBCConnection();
//        if(connection != null){
//            System.out.println("Thanh cong");
//        }else{
//            System.out.println("That bai");
//        }
//        
//        // Câu lệnh SQL tạo bảng
//        String createTableSQL = "CREATE TABLE KhachHang (" +
//                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
//                        "ten VARCHAR(100) NOT NULL, " +
//                        "email VARCHAR(100) UNIQUE NOT NULL, " +
//                        "sdt VARCHAR(15) NOT NULL, " +
//                        "diaChi TEXT, " +
//                        "ngayDangKy TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
//                        ");";
//
//
//        try (
//             java.sql.Statement statement = connection.createStatement()) {
//
//            // Thực thi câu lệnh tạo bảng
//            statement.execute(createTableSQL);
//            System.out.println("Bảng KhachHang đã được tạo thành công!");
//
//        } catch (SQLException e) {
//            System.err.println("Lỗi khi tạo bảng KhachHang:");
//            e.printStackTrace();
//        }
//    }
}
