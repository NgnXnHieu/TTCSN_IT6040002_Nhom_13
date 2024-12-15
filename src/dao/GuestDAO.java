package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Guest;
import ConnectDB.connection.JDBCConnection;  // Import JDBCConnection để sử dụng kết nối

public class GuestDAO {

    // Method to add a new guest
    public void addGuest(Guest guest) {
        String sql = "INSERT INTO Guest (ID, Name, SDT, Address, Email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, guest.getID());
            pstmt.setString(2, guest.getName());
            pstmt.setString(3, guest.getSDT());
            pstmt.setString(4, guest.getAddress());
            pstmt.setString(5, guest.getEmail());
            pstmt.executeUpdate();
            System.out.println("Guest added: " + guest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a guest by ID
    public boolean updateGuest(String id, Guest updatedGuest) {
        String sql = "UPDATE Guest SET Name = ?, SDT = ?, Address = ?, Email = ? WHERE ID = ?";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedGuest.getName());
            pstmt.setString(2, updatedGuest.getSDT());
            pstmt.setString(3, updatedGuest.getAddress());
            pstmt.setString(4, updatedGuest.getEmail());
            pstmt.setString(5, id);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a guest by ID
    public boolean deleteGuest(String id) {
        String sql = "DELETE FROM Guest WHERE ID = ?";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a guest by ID
    public Guest getGuestById(String id) {
        String sql = "SELECT * FROM Guest WHERE ID = ?";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Guest(
                        rs.getString("ID"),
                        rs.getString("Name"),
                        rs.getString("SDT"),
                        rs.getString("Address"),
                        rs.getString("Email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Method to get all guests
    public List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM Guest";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Guest guest = new Guest(
                        rs.getString("ID"),
                        rs.getString("Name"),
                        rs.getString("SDT"),
                        rs.getString("Address"),
                        rs.getString("Email")
                );
                guests.add(guest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }
}
