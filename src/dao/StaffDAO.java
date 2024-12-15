package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Staff;
import ConnectDB.connection.JDBCConnection;

public class StaffDAO {

    // Thêm nhân viên
    public boolean addStaff(Staff staff) {
        String sql = "INSERT INTO Staff (Name, Sdt, Email, IdUser) VALUES (?, ?, ?, ?)";
        return executeUpdate(sql, staff.getName(), staff.getSdt(), staff.getEmail(), staff.getIdUser());
    }

    // Sửa thông tin nhân viên
    public boolean updateStaff(Staff staff) {
        String sql = "UPDATE Staff SET Name = ?, Sdt = ?, Email = ?, IdUser = ? WHERE Id = ?";
        return executeUpdate(sql, staff.getName(), staff.getSdt(), staff.getEmail(), staff.getIdUser());
    }

    // Xóa nhân viên
    public boolean deleteStaff(String id) {
        String sql = "DELETE FROM Staff WHERE Id = ?";
        return executeUpdate(sql, id);
    }

    // Lấy danh sách tất cả nhân viên
    public List<Staff> getAllStaff() {
        String sql = "SELECT * FROM Staff";
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = JDBCConnection.getJDBCConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                staffList.add(mapResultSetToStaff(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    // Tìm nhân viên theo ID
    public Staff getStaffById(String id) {
        String sql = "SELECT * FROM Staff WHERE Id = ?";
        try (Connection connection = JDBCConnection.getJDBCConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToStaff(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Hàm hỗ trợ: Thực thi các câu lệnh INSERT, UPDATE, DELETE
    private boolean executeUpdate(String sql, Object... params) {
        try (Connection connection = JDBCConnection.getJDBCConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm hỗ trợ: Ánh xạ ResultSet sang đối tượng Staff
    private Staff mapResultSetToStaff(ResultSet rs) throws SQLException {
        return new Staff(
               
                rs.getString("Name"),
                rs.getString("Sdt"),
                rs.getString("Email"),
                rs.getString("IdUser")
        );
    }
    
    // Tìm nhân viên theo IdUser
public Staff getStaffByIdUser(String idUser) {
    String sql = "SELECT * FROM Staff WHERE IdUser = ?";
    try (Connection connection = JDBCConnection.getJDBCConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, idUser);
        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return mapResultSetToStaff(rs);  // Trả về nhân viên đầu tiên tìm thấy
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;  // Nếu không tìm thấy nhân viên nào
}
// Sửa thông tin nhân viên theo IdUser
public boolean updateStaffByIdUser(Staff staff) {
    String sql = "UPDATE Staff SET Name = ?, Sdt = ?, Email = ?, IdUser = ? WHERE IdUser = ?";
    return executeUpdate(sql, staff.getName(), staff.getSdt(), staff.getEmail(), staff.getIdUser(), staff.getIdUser());
}
// Sửa tên nhân viên theo IdUser
public boolean updateStaffNameByIdUser(String idUser, String newName) {
    String sql = "UPDATE Staff SET Name = ? WHERE IdUser = ?";
    return executeUpdate(sql, newName, idUser);
}


}
