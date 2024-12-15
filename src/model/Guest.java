/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author abc
 */
public class Guest {
    private String ID;
    private String Name;
    private String SDT;
    private String Address;
    private String Email;

    // Constructor
    public Guest(String ID, String Name, String SDT, String Address, String Email) {
        this.ID = ID;
        this.Name = Name;
        this.SDT = SDT;
        this.Address = Address;
        this.Email = Email;
    }

    // Getters and Setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    // toString Method
    @Override
    public String toString() {
        return "Guest{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", SDT='" + SDT + '\'' +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}

