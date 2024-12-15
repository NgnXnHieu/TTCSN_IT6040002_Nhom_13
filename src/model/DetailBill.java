package model;

public class DetailBill {
    private String idBill;
    private String idLaptop;
    private String idGuest;
    private Double price;
    private int quantity;

    // Constructor không tham số
    public DetailBill() {
    }

    // Constructor đầy đủ tham số
    public DetailBill(String idBill, String idLaptop, String idGuest, Double price, int quantity) {
        this.idBill = idBill;
        this.idLaptop = idLaptop;
        this.idGuest = idGuest;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter và Setter
    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdLaptop() {
        return idLaptop;
    }

    public void setIdLaptop(String idLaptop) {
        this.idLaptop = idLaptop;
    }

    public String getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(String idGuest) {
        this.idGuest = idGuest;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "DetailBill{" +
                "idBill='" + idBill + '\'' +
                ", idLaptop='" + idLaptop + '\'' +
                ", idGuest='" + idGuest + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
    
}
