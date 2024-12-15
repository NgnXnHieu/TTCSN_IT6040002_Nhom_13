package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Bill;
import ConnectDB.connection.JDBCConnection;  // Import JDBCConnection để sử dụng kết nối

public class BillDAO {

    // Method to add a new bill
    public boolean addBill(Bill bill) {
        String query = "INSERT INTO bills (ID, IdLaptop, IdGuest, date, Price, Quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, bill.getID());
            
            stmt.setString(2, bill.getIdGuest());
            stmt.setDate(3, new Date(bill.getDate().getTime())); // Sử dụng java.sql.Date cho ngày
            stmt.setDouble(4, bill.getPrice());
            stmt.setInt(5, bill.getQuantity());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve all bills
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM bills";
        try (Connection connection = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                bills.add(new Bill(
                        rs.getString("ID"),
                        rs.getString("IdGuest"),
                        rs.getDate("date"), // Lấy ngày dưới dạng java.sql.Date
                        rs.getDouble("Price"),
                        rs.getInt("Quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    // Method to update a bill by ID
    public boolean updateBill(Bill bill) {
        String query = "UPDATE bills SET IdLaptop = ?, IdGuest = ?, date = ?, Price = ?, Quantity = ? WHERE ID = ?";
        try (Connection connection = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement stmt = connection.prepareStatement(query)) {

           
            stmt.setString(1, bill.getIdGuest());
            stmt.setDate(2, new Date(bill.getDate().getTime())); // Sử dụng java.sql.Date cho ngày
            stmt.setDouble(3, bill.getPrice());
            stmt.setInt(4, bill.getQuantity());
            stmt.setString(5, bill.getID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a bill by ID
    public boolean deleteBill(String id) {
        String query = "DELETE FROM bills WHERE ID = ?";
        try (Connection connection = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a bill by ID
    public Bill getBillById(String id) {
        String query = "SELECT * FROM bills WHERE ID = ?";
        try (Connection connection = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Bill(
                            rs.getString("ID"),
                         
                            rs.getString("IdGuest"),
                            rs.getDate("date"), // Lấy ngày dưới dạng java.sql.Date
                            rs.getDouble("Price"),
                            rs.getInt("Quantity")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Date getBillDateById(String id) {
        String sql = "SELECT date FROM Bills WHERE ID = ?";
        try (Connection connection = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id); // Thiết lập id sản phẩm
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDate("date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Lấy tổng số tiền của tất cả hóa đơn
public double getTotalPrice() {
    String query = "SELECT SUM(Price * Quantity) FROM bills"; // Tính tổng số tiền = Giá * Số lượng
    try (Connection connection = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
         PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
            return rs.getDouble(1); // Trả về tổng số tiền
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Trả về 0 nếu không có dữ liệu
}

// Method to get the total quantity of all bills
public int getTotalQuantity() {
    String query = "SELECT SUM(Quantity) FROM bills"; // Tính tổng số lượng trong tất cả các hóa đơn
    try (Connection connection = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
         PreparedStatement pstmt = connection.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

        if (rs.next()) {
            return rs.getInt(1); // Trả về tổng số lượng
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Trả về 0 nếu không có dữ liệu
}

// Lấy ra tổng số lượng và tổng tiền theo idLaptop
public Double[] getTotalQuantityAndAmountByIdLaptop(String idLaptop) {
    String query = "SELECT SUM(Quantity) AS totalQuantity, SUM(Price * Quantity) AS totalAmount FROM bills WHERE IdLaptop = ?";
    Double[] result = new Double[2]; // index 0: totalQuantity, index 1: totalAmount
    
    try (Connection connection = JDBCConnection.getJDBCConnection(); // Sử dụng kết nối từ JDBCConnection
         PreparedStatement pstmt = connection.prepareStatement(query)) {
        
        pstmt.setString(1, idLaptop); // Thiết lập idLaptop
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                result[0] = rs.getDouble("totalQuantity"); // Lấy tổng số lượng
                result[1] = rs.getDouble("totalAmount");   // Lấy tổng số tiền
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result; // Trả về mảng [totalQuantity, totalAmount]
}

    // trả về mảng idlaptop theo tháng muốn tìm
public List<String> getDistinctIdLaptopsByMonthInCurrentYear(String month) {
    List<String> idLaptops = new ArrayList<>();
    
    // Lấy năm hiện tại
    int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    
    // Kiểm tra và chuẩn hóa tháng: nếu tháng chỉ có 1 chữ số, thêm "0" vào phía trước
    if (month.length() == 1) {
        month = "0" + month; // Thêm số 0 phía trước nếu tháng chỉ có một chữ số
    }

    // Câu truy vấn SQL để lấy các idLaptop không trùng trong tháng và năm hiện tại
    String query = "SELECT DISTINCT IdLaptop FROM bills WHERE DATE_FORMAT(date, '%Y-%m') = ? AND YEAR(date) = ?";
    
    try (Connection connection = JDBCConnection.getJDBCConnection(); // Sử dụng kết nối từ JDBCConnection
         PreparedStatement pstmt = connection.prepareStatement(query)) {
        
        pstmt.setString(1, currentYear + "-" + month); // Thiết lập tháng theo định dạng "YYYY-MM"
        pstmt.setInt(2, currentYear); // Thiết lập năm hiện tại
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Thêm idLaptop vào danh sách
                idLaptops.add(rs.getString("IdLaptop"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return idLaptops; // Trả về danh sách các idLaptop không trùng
}


// Method to get distinct IdLaptops within a date range with single-digit day/month
public List<String> getDistinctIdLaptopsByDateRange(String ngay1, String thang1, String nam1, String ngay2, String thang2, String nam2) {
    List<String> idLaptops = new ArrayList<>();
    
    // Chuyển đổi các tham số ngày, tháng, năm thành định dạng ngày tháng năm chuẩn (thêm số 0 cho ngày/tháng nếu cần thiết)
    String startDate = nam1 + "-" + (thang1.length() == 1 ? "0" + thang1 : thang1) + "-" + (ngay1.length() == 1 ? "0" + ngay1 : ngay1);
    String endDate = nam2 + "-" + (thang2.length() == 1 ? "0" + thang2 : thang2) + "-" + (ngay2.length() == 1 ? "0" + ngay2 : ngay2);
    
    // Câu truy vấn SQL để lấy các idLaptop trong khoảng thời gian
    String query = "SELECT DISTINCT IdLaptop FROM bills WHERE date BETWEEN ? AND ?";
    
    try (Connection connection = JDBCConnection.getJDBCConnection(); // Sử dụng kết nối từ JDBCConnection
         PreparedStatement pstmt = connection.prepareStatement(query)) {
        
        // Thiết lập giá trị ngày bắt đầu và ngày kết thúc cho câu truy vấn
        pstmt.setString(1, startDate); // Ngày bắt đầu
        pstmt.setString(2, endDate);   // Ngày kết thúc
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Thêm idLaptop vào danh sách
                idLaptops.add(rs.getString("IdLaptop"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return idLaptops; // Trả về danh sách các idLaptop
}



}

