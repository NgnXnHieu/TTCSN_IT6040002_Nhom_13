package model;

public class Staff {

    private String Name;
    private String Sdt;
    private String Email;
    private String IdUser;

    // Constructor không tham số
    public Staff() {
    }

    // Constructor có tham số
    public Staff(String name, String sdt, String email, String idUser) {
        
        this.Name = name;
        this.Sdt = sdt;
        this.Email = email;
        this.IdUser = idUser;
    }

    // Getter và Setter cho từng thuộc tính
   
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        this.Sdt = sdt;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        this.IdUser = idUser;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Staff{" +
   
                ", Name='" + Name + '\'' +
                ", Sdt='" + Sdt + '\'' +
                ", Email='" + Email + '\'' +
                ", IdUser='" + IdUser + '\'' +
                '}';
    }
}
