/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author abc
 */
public class User {
  
    private String userName;
    private String password;
    private boolean role;  // true: Admin, false: User
    private boolean status; // true: Active, false: Inactive

    // Constructor không tham số (mặc định)
    public User() {
    }

    // Constructor đầy đủ tham số
    public User( String userName, String password, boolean role, boolean status) {
      
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    // Getter và Setter
  

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "User{" +
            
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + (role ? "Admin" : "User") +
                ", status=" + (status ? "Active" : "Inactive") +
                '}';
    }
    
    
}
