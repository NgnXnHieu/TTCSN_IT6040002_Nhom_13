
package bean;


import javax.swing.JLabel;


public class DanhMucBean {

    private JLabel jlb;
    private String kind;

    public DanhMucBean() {
    }

    public DanhMucBean( JLabel jlb, String kind) {
        this.jlb = jlb;
        this.kind = kind;
    }
//    ArrayList<DanhMucBean> list = new ArrayList<>();



    public JLabel getJlb() {
        return jlb;
    }

    public void setJlb(JLabel jlb) {
        this.jlb = jlb;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
    
}

