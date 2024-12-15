package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bean.DanhMucBean;
import views.BillPanel;
import views.GuestPanel;
import views.InforUser;
import views.ProductPanel;
import views.StaffPanel;
import views.RevenuePanel;
import views.InforUser;
import views.MainMenu;
import views.RevenuePanel;
import views.StaffPanel;


public class changeview_controller extends DanhMucBean {

    private JPanel jplRoot;
    private String kind_slected = "";

    public changeview_controller(JPanel jplRoot) {
        this.jplRoot = jplRoot;
    }

    public void setview(JLabel jliteam) {
        kind_slected = "TrangChu";
        jliteam.setBackground(Color.red);
        jplRoot.removeAll();
        jplRoot.setLayout(new BorderLayout());
        jplRoot.add(new GuestPanel());
        jplRoot.validate();
        jplRoot.repaint();
    }
    public void setEven(ArrayList<DanhMucBean> list) {
        for (DanhMucBean danhMucBean : list) {
           danhMucBean.getJlb().addMouseListener(new labeleven(danhMucBean.getKind(),danhMucBean.getJlb()));

        }
    }

    class labeleven implements MouseListener {
        private JPanel node;
        private String kind;
        private JLabel jlbitem;
        public labeleven(String kind, JLabel jlbitem) {
            this.kind = kind;
            this.jlbitem = jlbitem;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "Menu":
                    node = new MainMenu();
                    break;
                case "Product":
                    node = new ProductPanel();
                    break;
                case "Guest":
                    node = new GuestPanel();
                    break;
                case "Bill":
                    node = new BillPanel();
                    break;
                case "Staff":
                    node = new StaffPanel();
                    break;
                case "Revenue":
                    node = new RevenuePanel();
                    break;
                case "InforUser":
                    node = new InforUser();
                    break;
                default:
                    break;

            }
            jplRoot.removeAll();
            jplRoot.setLayout(new BorderLayout());
            jplRoot.add(node);
            jplRoot.validate();
            jplRoot.repaint();

//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mousePressed(MouseEvent e) {
//          throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mouseReleased(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mouseEntered(MouseEvent e) {
                jlbitem.setForeground(Color.red);

//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kind_slected.equalsIgnoreCase(kind)) {
                jlbitem.setForeground(Color.BLACK);

            }
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }

}
