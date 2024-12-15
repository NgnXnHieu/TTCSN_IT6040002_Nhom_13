package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DetailBill;
import ConnectDB.connection.JDBCConnection;  // Import JDBCConnection để sử dụng kết nối

public class DetailBillDAO {

    private Connection connection;

    // Constructor để khởi tạo kết nối từ JDBCConnection
    public DetailBillDAO() {
        this.connection = JDBCConnection.getJDBCConnection(); // Sử dụng phương thức getConnection của JDBCConnection
    }

    // Phương thức thêm mới một DetailBill
    public boolean insertDetailBill(DetailBill detailBill) {
        String query = "INSERT INTO DetailBill (IdBill, IdLaptop, IdGuest, price, quantity) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, detailBill.getIdBill());
            statement.setString(2, detailBill.getIdLaptop());
            statement.setString(3, detailBill.getIdGuest());
            statement.setDouble(4, detailBill.getPrice());
            statement.setInt(5, detailBill.getQuantity());
            return statement.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức cập nhật một DetailBill
    public boolean updateDetailBill(DetailBill detailBill) {
        String query = "UPDATE DetailBill SET IdLaptop = ?, IdGuest = ?, price = ?, quantity = ? WHERE IdBill = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, detailBill.getIdLaptop());
            statement.setString(2, detailBill.getIdGuest());
            statement.setDouble(3, detailBill.getPrice());
            statement.setInt(4, detailBill.getQuantity());
            statement.setString(5, detailBill.getIdBill());
            return statement.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức xóa một DetailBill theo IdBill
    public boolean deleteDetailBill(String idBill) {
        String query = "DELETE FROM DetailBill WHERE IdBill = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idBill);
            return statement.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Phương thức lấy danh sách tất cả DetailBill
    public List<DetailBill> getAllDetailBills() {
        List<DetailBill> detailBills = new ArrayList<>();
        String query = "SELECT * FROM DetailBill";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                DetailBill detailBill = new DetailBill(
                        resultSet.getString("IdBill"),
                        resultSet.getString("IdLaptop"),
                        resultSet.getString("IdGuest"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );
                detailBills.add(detailBill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailBills;
    }

    // Phương thức tìm DetailBill theo IdBill
    public DetailBill getDetailBillById(String idBill) {
        String query = "SELECT * FROM DetailBill WHERE IdBill = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idBill);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new DetailBill(
                            resultSet.getString("IdBill"),
                            resultSet.getString("IdLaptop"),
                            resultSet.getString("IdGuest"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức tính tổng price và quantity theo IdBill
    // Phương thức tính tổng price và quantity theo IdBill
public DetailBill getTotalByIdBill(String idBill) {
    String query = "SELECT IdBill, SUM(price) AS total_price, SUM(quantity) AS total_quantity " +
                   "FROM DetailBill WHERE IdBill = ? GROUP BY IdBill";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, idBill);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new DetailBill(
                        resultSet.getString("IdBill"),
                        null, // Không cần IdLaptop
                        null, // Không cần IdGuest
                        resultSet.getDouble("total_price"),
                        resultSet.getInt("total_quantity")
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    // Nếu không tìm thấy, trả về một đối tượng rỗng với giá trị mặc định
    return new DetailBill(idBill, null, null, 0.0, 0);
}

// Phương thức lấy danh sách DetailBill theo IdBill
    public List<DetailBill> getDetailBillsByIdBill(String idBill) {
        List<DetailBill> detailBills = new ArrayList<>();
        String query = "SELECT * FROM DetailBill WHERE IdBill = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idBill); // Thiết lập giá trị cho tham số trong câu truy vấn
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Lấy thông tin từ ResultSet và tạo đối tượng DetailBill
                    DetailBill detailBill = new DetailBill(
                            resultSet.getString("IdBill"),
                            resultSet.getString("IdLaptop"),
                            resultSet.getString("IdGuest"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity")
                    );
                    detailBills.add(detailBill); // Thêm đối tượng vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return detailBills; // Trả về danh sách DetailBill
    }
    
    // Phương thức tính tổng price và quantity theo IdLaptop
public DetailBill getTotalByIdLaptop(String idLaptop) {
    String query = "SELECT IdLaptop, SUM(price) AS total_price, SUM(quantity) AS total_quantity " +
                   "FROM DetailBill WHERE IdLaptop = ? GROUP BY IdLaptop";
    
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, idLaptop); // Thiết lập giá trị cho tham số IdLaptop
        
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new DetailBill(
                        null, // Không cần IdBill
                        resultSet.getString("IdLaptop"), // Lấy IdLaptop
                        null, // Không cần IdGuest
                        resultSet.getDouble("total_price"), // Lấy tổng giá
                        resultSet.getInt("total_quantity") // Lấy tổng số lượng
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    // Nếu không tìm thấy, trả về một đối tượng rỗng với giá trị mặc định
    return new DetailBill(null, idLaptop, null, 0.0, 0);
}

    // Phương thức tính tổng price và quantity của tất cả các phần tử
public DetailBill getTotal() {
    String query = "SELECT SUM(price) AS total_price, SUM(quantity) AS total_quantity FROM DetailBill";
    
    try (PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
            return new DetailBill(
                    null,  // Không cần IdBill
                    null,  // Không cần IdLaptop
                    null,  // Không cần IdGuest
                    resultSet.getDouble("total_price"), // Lấy tổng giá
                    resultSet.getInt("total_quantity") // Lấy tổng số lượng
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    // Nếu không tìm thấy, trả về một đối tượng rỗng với giá trị mặc định
    return new DetailBill(null, null, null, 0.0, 0);
}

    public List<String> getDistinctIdBillsByMonthInCurrentYear(String month) {
    List<String> idBills = new ArrayList<>();
    
    // Lấy năm hiện tại
    int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    
    // Kiểm tra và chuẩn hóa tháng: nếu tháng chỉ có 1 chữ số, thêm "0" vào phía trước
    if (month.length() == 1) {
        month = "0" + month; // Thêm số 0 phía trước nếu tháng chỉ có một chữ số
    }

    // Câu truy vấn SQL để lấy các IdBill không trùng trong tháng và năm hiện tại
    String query = "SELECT DISTINCT IdBill FROM bills WHERE DATE_FORMAT(date, '%Y-%m') = ? AND YEAR(date) = ?";
    
    try (Connection connection = JDBCConnection.getJDBCConnection(); // Sử dụng kết nối từ JDBCConnection
         PreparedStatement pstmt = connection.prepareStatement(query)) {
        
        pstmt.setString(1, currentYear + "-" + month); // Thiết lập tháng theo định dạng "YYYY-MM"
        pstmt.setInt(2, currentYear); // Thiết lập năm hiện tại
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Thêm IdBill vào danh sách
                idBills.add(rs.getString("IdBill"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return idBills; // Trả về danh sách các IdBill không trùng
}


}
