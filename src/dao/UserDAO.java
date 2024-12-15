package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;
import ConnectDB.connection.JDBCConnection;  // Import JDBCConnection

public class UserDAO {

    // Method to add a user to the database
    public void addUser(User user) {
    // Sửa câu lệnh SQL chỉ còn 4 tham số
    String sql = "INSERT INTO User (UserName, Password, Role, Status) VALUES (?, ?, ?, ?)";

    try (Connection conn = JDBCConnection.getJDBCConnection(); // Use connection from JDBCConnection
         PreparedStatement statement = conn.prepareStatement(sql)) {

        // Gán giá trị cho 4 tham số
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getPassword());
        statement.setBoolean(3, user.isRole());
        statement.setBoolean(4, user.isStatus());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Đã thêm người dùng thành công!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Method to get all users from the database
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection conn = JDBCConnection.getJDBCConnection(); // Use connection from JDBCConnection
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString("UserName"));
                user.setPassword(resultSet.getString("Password"));
                user.setRole(resultSet.getBoolean("Role"));
                user.setStatus(resultSet.getBoolean("Status"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Method to get a user by username
    public User getUserByUserName(String userName) {
        String sql = "SELECT * FROM User WHERE UserName = ?";
        User user = null;

        try (Connection conn = JDBCConnection.getJDBCConnection(); // Use connection from JDBCConnection
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
           
                user.setUserName(resultSet.getString("UserName"));
                user.setPassword(resultSet.getString("Password"));
                user.setRole(resultSet.getBoolean("Role"));
                user.setStatus(resultSet.getBoolean("Status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // Method to update user information
    public void updateUser(User user) {
        String sql = "UPDATE User SET  Password = ?, Role = ?, Status = ? WHERE UserName = ?";

        try (Connection conn = JDBCConnection.getJDBCConnection(); // Use connection from JDBCConnection
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getPassword());
            statement.setBoolean(2, user.isRole());
            statement.setBoolean(3, user.isStatus());
            statement.setString(4, user.getUserName());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thông tin người dùng thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a user by username
    public void deleteUser(String userName) {
        String sql = "DELETE FROM User WHERE UserName = ?";

        try (Connection conn = JDBCConnection.getJDBCConnection(); // Use connection from JDBCConnection
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, userName);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Đã xóa người dùng thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Lấy Id bằng tên UserName
    public String getIdByUserName(String userName) {
        String sql = "SELECT IdUser FROM User WHERE userName = ?";
        try (Connection connection = JDBCConnection.getJDBCConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("IdUser");  // Giả sử trường Id có kiểu dữ liệu String
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Nếu không tìm thấy userName trong bảng
    }
    
     // Phương thức cập nhật mật khẩu của người dùng theo userId
    public boolean updatePassword(int userId, String newPassword) {
        String sql = "UPDATE user SET Password = ? WHERE IdUser = ?";
        try (Connection conn = JDBCConnection.getJDBCConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Nếu có bản ghi bị ảnh hưởng, trả về true
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Lấy mật khẩu theo id
public String getPasswordById(int userId) {
    String sql = "SELECT Password FROM User WHERE IdUser = ?";
    String password = null;

    try (Connection conn = JDBCConnection.getJDBCConnection(); // Use connection from JDBCConnection
         PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setInt(1, userId);  // Set userId parameter

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                password = resultSet.getString("Password");  // Get password field
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return password;  // Return password or null if not found
}


}
