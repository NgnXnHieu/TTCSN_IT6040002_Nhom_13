/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author abc
 */
public class Product {
    private String ID;
    private String ProductName;
    private String BrandName;
    private double Price;
    private int Quantity;

    // Constructor
    public Product(String ID, String ProductName, String BrandName, double Price, int Quantity) {
        this.ID = ID;
        this.ProductName = ProductName;
        this.BrandName = BrandName;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    // Getters and Setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    // toString Method
    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", BrandName='" + BrandName + '\'' +
                ", Price=" + Price +
                ", Quantity=" + Quantity +
                '}';
    }
}
