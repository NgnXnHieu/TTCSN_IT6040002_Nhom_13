package model;

import java.util.Date;

public class Bill {
    private String ID;
    private String IdGuest;
    private Date date;
    private Double Price;
    private int Quantity;

    // Constructor
    public Bill(String ID,  String IdGuest, Date date, Double Price, int Quantity) {
        this.ID = ID;
        this.IdGuest = IdGuest;
        this.date = date;
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

    public String getIdGuest() {
        return IdGuest;
    }

    public void setIdGuest(String IdGuest) {
        this.IdGuest = IdGuest;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    // toString method
    @Override
    public String toString() {
        return "Bill{" +
                "ID='" + ID + '\'' +
                ", IdGuest='" + IdGuest + '\'' +
                ", date='" + date + '\'' +
                ", Price=" + Price +
                ", Quantity=" + Quantity +
                '}';
    }
}
