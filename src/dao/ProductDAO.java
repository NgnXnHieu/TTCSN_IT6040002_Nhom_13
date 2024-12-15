package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import ConnectDB.connection.JDBCConnection;  // Import JDBCConnection để sử dụng kết nối

public class ProductDAO {

    // Method to add a new product
    public void addProduct(Product product) {
        String sql = "INSERT INTO Product (ID, ProductName, BrandName, Price, Quantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getID());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getBrandName());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getQuantity());
            pstmt.executeUpdate();
            System.out.println("Product added: " + product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a product by ID
    public boolean updateProduct(String id, Product updatedProduct) {
        String sql = "UPDATE Product SET ProductName = ?, BrandName = ?, Price = ?, Quantity = ? WHERE ID = ?";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedProduct.getProductName());
            pstmt.setString(2, updatedProduct.getBrandName());
            pstmt.setDouble(3, updatedProduct.getPrice());
            pstmt.setInt(4, updatedProduct.getQuantity());
            pstmt.setString(5, id);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a product by ID
    public boolean deleteProduct(String id) {
        String sql = "DELETE FROM Product WHERE ID = ?";
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

    // Method to get a product by ID
    public Product getProductById(String id) {
        String sql = "SELECT * FROM Product WHERE ID = ?";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getString("ID"),
                        rs.getString("ProductName"),
                        rs.getString("BrandName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no product is found
    }

    // Method to get all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("ID"),
                        rs.getString("ProductName"),
                        rs.getString("BrandName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Method to get product details (Name and Brand) by ID
    public String[] getProductDetailsById(String id) {
        String query = "SELECT ProductName, BrandName FROM Product WHERE ID = ?";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String productName = rs.getString("ProductName");
                    String brandName = rs.getString("BrandName");
                    return new String[]{productName, brandName};
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no data is found or error occurs
    }

    // Method to get product price by ID
    public double getProductPriceById(String id) {
        String sql = "SELECT Price FROM Product WHERE ID = ?";
        try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Price"); // Return product price
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if no product is found
    }
    
    // Method to get product name by ID
public String getProductNameById(String id) {
    String sql = "SELECT ProductName FROM Product WHERE ID = ?";
    try (Connection conn = JDBCConnection.getJDBCConnection();  // Sử dụng kết nối từ JDBCConnection
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("ProductName"); // Return product name
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Return null if no product is found
}

}
