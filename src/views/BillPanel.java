/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import dao.BillDAO;
import dao.DetailBillDAO;
import dao.GuestDAO;
import dao.ProductDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Bill;
import model.DetailBill;
import model.Guest;
import model.Product;

/**
 *
 * @author abc
 */
public class BillPanel extends javax.swing.JPanel {
    private BillDAO bDAO = new  BillDAO();
    private ProductDAO pDAO = new  ProductDAO();
    private List<Bill> listBill;
    private List<Product> listLaptop= pDAO.getAllProducts();
    private GuestDAO gDAO = new GuestDAO();
    private List<Guest> listGuest= gDAO.getAllGuests();
    private List<Product> products;
    private DetailBillDAO dbDAO = new DetailBillDAO();
    private List<DetailBill> listAdd;
    private List<Product> list1;
    private String id;
    private List<DetailBill> list2;
    private List<DetailBill> listDB = new ArrayList<>();
    private List<DetailBill> list3;    

    /**
     * Creates new form Bill
     */
            
     private void setTable() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jTable1.setModel(defaultTableModel);
        ListSelectionModel listSelectionModel = jTable1.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Mã Hóa Đơn");
        defaultTableModel.addColumn("Mã khách hàng");
        defaultTableModel.addColumn("Ngày lập");
        defaultTableModel.addColumn("Giá bán");
        defaultTableModel.addColumn("Số lượng");
        listBill = bDAO.getAllBills();

        // Duyệt qua danh sách products và thêm vào bảng
        int stt = 1; // Biến đánh số thứ tự
        for (Bill b : listBill) {
            DetailBill dB =  dbDAO.getTotalByIdBill(b.getID());
            Object[] row = new Object[6];
            row[0] = stt++;  // Số thứ tự
            row[1] = b.getID();
            row[2] = b.getIdGuest();
            row[3] = b.getDate();
            row[4] = dB.getPrice();
            row[5] = dB.getQuantity();
            defaultTableModel.addRow(row); // Thêm hàng vào bảng
        }

    }
     
     private void setTable_ADD() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        add_table.setModel(defaultTableModel);
        ListSelectionModel listSelectionModel = add_table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Mã Sản Phẩm");
        defaultTableModel.addColumn("Tên Laptop");
        defaultTableModel.addColumn("Tên hãng sản xuất");
        defaultTableModel.addColumn("Giá bán");
        defaultTableModel.addColumn("Số lượng chọn mua");
        products = pDAO.getAllProducts();

        // Duyệt qua danh sách products và thêm vào bảng
        int stt = 1; // Biến đánh số thứ tự
        for (Product product : products) {
            Object[] row = new Object[6];
            row[0] = stt++;  // Số thứ tự
            row[1] = product.getID();
            row[2] = product.getProductName();
            row[3] = product.getBrandName();
            row[4] = product.getPrice();
            row[5] = 0;
            defaultTableModel.addRow(row); // Thêm hàng vào bảng
        }
        
        // Tạo menu chuột phải
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editMenuItem = new JMenuItem("Edit");
        JMenuItem deleteMenuItem = new JMenuItem("Delete");

        // Thêm các item vào menu
        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);

        // Lắng nghe sự kiện chuột trái trên JTable
        add_table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Kiểm tra là chuột trái
                    int row = add_table.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        add_table.setRowSelectionInterval(row, row); // Chọn dòng khi click
                        
                        // Chức năng
                        dialog_addProduct.setVisible(true);
                        dialog_addProduct.setSize(350,370);
                        Lbl_MSP.setText((String) add_table.getValueAt(row, 1));
                        Lbl_TSP.setText((String) add_table.getValueAt(row, 2));
                        Lbl_TH.setText((String) add_table.getValueAt(row, 3));
                        Sp_Sl.setValue(add_table.getValueAt(row, 5));
                    }
                }
            }
        });
        
     } 
        
        private void setTable_AddProduct() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        edit_ProductTable.setModel(defaultTableModel);
        ListSelectionModel listSelectionModel = edit_ProductTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Mã Sản Phẩm");
        defaultTableModel.addColumn("Tên Laptop");
        defaultTableModel.addColumn("Tên hãng sản xuất");
        defaultTableModel.addColumn("Giá bán");
        defaultTableModel.addColumn("Số lượng chọn mua");
        products = pDAO.getAllProducts();

        // Duyệt qua danh sách products và thêm vào bảng
        int stt = 1; // Biến đánh số thứ tự
        for (Product product : products) {
            Object[] row = new Object[6];
            row[0] = stt++;  // Số thứ tự
            row[1] = product.getID();
            row[2] = product.getProductName();
            row[3] = product.getBrandName();
            row[4] = product.getPrice();
            row[5] = 0;
            defaultTableModel.addRow(row); // Thêm hàng vào bảng
        }
        
        // Tạo menu chuột phải
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editMenuItem = new JMenuItem("Edit");
        JMenuItem deleteMenuItem = new JMenuItem("Delete");

        // Thêm các item vào menu
        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);

        // Lắng nghe sự kiện chuột trái trên JTable
        edit_ProductTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Kiểm tra là chuột trái
                    int row = edit_ProductTable.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        edit_ProductTable.setRowSelectionInterval(row, row); // Chọn dòng khi click
                        
                        // Chức năng
                        dialog_addProduct1.setVisible(true);
                        dialog_addProduct1.setSize(350,370);
                        Lbl_MSP1.setText((String) edit_ProductTable.getValueAt(row, 1));
                        Lbl_TSP1.setText((String) edit_ProductTable.getValueAt(row, 2));
                        Lbl_TH1.setText((String) edit_ProductTable.getValueAt(row, 3));
                        Sp_Sl1.setValue(edit_ProductTable.getValueAt(row, 5));
                    }
                }
            }
        });
}


    
     
     
     private void setTable_Edit(String idBill) {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        edit_table.setModel(defaultTableModel);
        ListSelectionModel listSelectionModel = edit_table.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Mã Sản phẩm");
        defaultTableModel.addColumn("Tên Laptop");
        defaultTableModel.addColumn("Tên hãng sản xuất");
        defaultTableModel.addColumn("Giá bán");
        defaultTableModel.addColumn("Số lượng chọn mua");
        

        // Duyệt qua danh sách DetailBills và thêm vào bảng
        int stt = 1; // Biến đánh số thứ tự
        for (DetailBill db : listDB) {
            Object[] row = new Object[6];
            row[0] = stt++;  // Số thứ tự
            row[1] = db.getIdLaptop();
            row[2] = pDAO.getProductNameById(db.getIdLaptop());
            row[3] = pDAO.getProductDetailsById(db.getIdLaptop())[1];
            row[4] = pDAO.getProductPriceById(db.getIdLaptop());
            row[5] = db.getQuantity();
//            list2= new  ArrayList<>();
//            DetailBill d =new DetailBill(id, db.getIdLaptop(), pDAO.getProductNameById(db.getIdLaptop()),pDAO.getProductPriceById(db.getIdLaptop()) , db.getQuantity());
//            list2.add(d);
            defaultTableModel.addRow(row); // Thêm hàng vào bảng
        }
        
        for(Product p : list1){
            Object[] row = new Object[6];
            row[0] = stt++;  // Số thứ tự
            row[1] = p.getID();
            row[2] = p.getProductName();
            row[3] = p.getBrandName();
            row[4] = p.getPrice();
            row[5] = p.getQuantity();
            defaultTableModel.addRow(row); // Thêm hàng vào bảng
        }
        
        // Tạo menu chuột phải
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editMenuItem = new JMenuItem("Edit");
        JMenuItem deleteMenuItem = new JMenuItem("Delete");

        // Thêm các item vào menu
        popupMenu.add(editMenuItem);
        popupMenu.add(deleteMenuItem);

        // Lắng nghe sự kiện chuột trái trên JTable
        add_table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Kiểm tra là chuột trái
                    int row = add_table.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        add_table.setRowSelectionInterval(row, row); // Chọn dòng khi click
                        
                        // Chức năng
                        dialog_addProduct.setVisible(true);
                        dialog_addProduct.setSize(350,370);
                        Lbl_MSP.setText((String) add_table.getValueAt(row, 1));
                        Lbl_TSP.setText((String) add_table.getValueAt(row, 2));
                        Lbl_TH.setText((String) add_table.getValueAt(row, 3));
                        Sp_Sl.setValue(add_table.getValueAt(row, 5));
                    }
                }
            }
        });


    }
     
    public BillPanel() {
        initComponents();
        setTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        add_dialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        add_idlb = new javax.swing.JLabel();
        add_brandlb = new javax.swing.JLabel();
        add_id = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        add_sumPrice = new javax.swing.JLabel();
        add_idGuestCB = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        add_pricelb1 = new javax.swing.JLabel();
        tongSoLuong = new javax.swing.JLabel();
        TongTien = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        add_table = new javax.swing.JTable();
        edit_dialog = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        edit_idBill = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        edit_idLaptopCB = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        edit_idGuestCB = new javax.swing.JComboBox<>();
        edit_quantitySp = new javax.swing.JSpinner();
        edit_sumPrice = new javax.swing.JLabel();
        dialog_addProduct = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Lbl_MSP = new javax.swing.JLabel();
        Lbl_TSP = new javax.swing.JLabel();
        Lbl_TH = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        Sp_Sl = new javax.swing.JSpinner();
        edit_newDialog = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        add_brandlb1 = new javax.swing.JLabel();
        add_idlb1 = new javax.swing.JLabel();
        edit_idGuestCB1 = new javax.swing.JComboBox<>();
        edit_MHD = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        edit_table = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        add_pricelb2 = new javax.swing.JLabel();
        edit_TongSL = new javax.swing.JLabel();
        edit_TongTien = new javax.swing.JLabel();
        add_sumPrice1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        edit_ProductTable = new javax.swing.JTable();
        dialog_addProduct1 = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Lbl_MSP1 = new javax.swing.JLabel();
        Lbl_TSP1 = new javax.swing.JLabel();
        Lbl_TH1 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        Sp_Sl1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        addBtn_Bill = new javax.swing.JButton();
        editBtn_Bill = new javax.swing.JButton();
        delBtn_Bill = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        add_dialog.setMinimumSize(new java.awt.Dimension(600, 400));
        add_dialog.getContentPane().setLayout(new javax.swing.BoxLayout(add_dialog.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thêm Hóa Đơn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add_idlb.setText("Mã hóa đơn");
        jPanel5.add(add_idlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        add_brandlb.setText("Mã khách hàng");
        jPanel5.add(add_brandlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        add_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_idActionPerformed(evt);
            }
        });
        jPanel5.add(add_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 190, -1));

        jButton4.setText("Hủy");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        add_sumPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add_sumPrice.setText("Tổng tiền :");
        jPanel5.add(add_sumPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jPanel5.add(add_idGuestCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 190, -1));

        jButton3.setText("Thêm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        add_pricelb1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add_pricelb1.setText("Số lượng");
        jPanel5.add(add_pricelb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        tongSoLuong.setText("0");
        jPanel5.add(tongSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));

        TongTien.setText("0");
        jPanel5.add(TongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, -1));

        jPanel4.add(jPanel5, java.awt.BorderLayout.LINE_START);

        add_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(add_table);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add_dialog.getContentPane().add(jPanel4);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Sửa Hóa Đơn");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        edit_dialog.getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));

        jLabel9.setText("Mã Hóa Đơn");

        jLabel10.setText("Mã Laptop");

        jLabel11.setText("Mã khách hàng");

        edit_idBill.setText("jLabel14");

        jButton6.setText("Sửa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Hủy");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        edit_idLaptopCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_idLaptopCBActionPerformed(evt);
            }
        });

        jLabel13.setText("Số lượng");

        edit_quantitySp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                edit_quantitySpStateChanged(evt);
            }
        });

        edit_sumPrice.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        edit_sumPrice.setText("Tổng tiền: ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jButton6)
                        .addGap(49, 49, 49)
                        .addComponent(jButton7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edit_sumPrice))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edit_idBill, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(edit_idLaptopCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edit_idGuestCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edit_quantitySp, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(edit_idBill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(edit_idLaptopCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(edit_idGuestCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(edit_quantitySp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(edit_sumPrice)
                .addGap(44, 44, 44)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        edit_dialog.getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Chọn sản phẩm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        dialog_addProduct.getContentPane().add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Mã sản phẩm");

        jLabel5.setText("Tên sản phẩm");

        jLabel6.setText("Tên hãng");

        Lbl_MSP.setText("jLabel7");

        Lbl_TSP.setText("jLabel7");

        Lbl_TH.setText("jLabel7");

        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setText("Số lượng");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jButton1)
                        .addGap(48, 48, 48)
                        .addComponent(jButton2))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lbl_TSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Lbl_TH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(Sp_Sl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 79, Short.MAX_VALUE))
                            .addComponent(Lbl_MSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Lbl_MSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Lbl_TSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Lbl_TH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(Sp_Sl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(29, 29, 29))
        );

        dialog_addProduct.getContentPane().add(jPanel9, java.awt.BorderLayout.CENTER);

        edit_newDialog.setMinimumSize(new java.awt.Dimension(600, 400));
        edit_newDialog.getContentPane().setLayout(new javax.swing.BoxLayout(edit_newDialog.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));

        add_brandlb1.setText("Mã khách hàng");

        add_idlb1.setText("Mã hóa đơn");

        edit_MHD.setText("jLabel12");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_idlb1)
                    .addComponent(add_brandlb1))
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edit_idGuestCB1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_MHD, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(578, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_idlb1)
                    .addComponent(edit_MHD))
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_brandlb1)
                    .addComponent(edit_idGuestCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));
        jPanel12.setLayout(new java.awt.BorderLayout());

        edit_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(edit_table);

        jPanel12.add(jScrollPane3, java.awt.BorderLayout.LINE_END);

        jButton5.setText("Hủy");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        add_pricelb2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add_pricelb2.setText("Số lượng");

        edit_TongSL.setText("0");

        edit_TongTien.setText("0");

        add_sumPrice1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        add_sumPrice1.setText("Tổng tiền :");

        jButton9.setText("Sửa Hóa Đơn");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(add_pricelb2)
                .addGap(33, 33, 33)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(edit_TongSL)
                    .addComponent(edit_TongTien))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9)
                    .addComponent(add_sumPrice1))
                .addGap(36, 36, 36)
                .addComponent(jButton5)
                .addGap(223, 223, 223))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_pricelb2)
                    .addComponent(edit_TongSL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_sumPrice1)
                    .addComponent(edit_TongTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton9))
                .addContainerGap())
        );

        jPanel12.add(jPanel14, java.awt.BorderLayout.PAGE_END);

        jPanel15.setLayout(new java.awt.BorderLayout());
        jPanel12.add(jPanel15, java.awt.BorderLayout.PAGE_START);

        jPanel10.add(jPanel12, java.awt.BorderLayout.LINE_START);

        edit_ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(edit_ProductTable);

        jPanel10.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        edit_newDialog.getContentPane().add(jPanel10);

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Chọn sản phẩm");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        dialog_addProduct1.getContentPane().add(jPanel13, java.awt.BorderLayout.PAGE_START);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setText("Mã sản phẩm");

        jLabel14.setText("Tên sản phẩm");

        jLabel16.setText("Tên hãng");

        Lbl_MSP1.setText("jLabel7");

        Lbl_TSP1.setText("jLabel7");

        Lbl_TH1.setText("jLabel7");

        jButton10.setText("Thêm");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Hủy");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel17.setText("Số lượng");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jButton10)
                        .addGap(48, 48, 48)
                        .addComponent(jButton11))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lbl_TSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Lbl_TH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(Sp_Sl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 79, Short.MAX_VALUE))
                            .addComponent(Lbl_MSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(Lbl_MSP1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(Lbl_TSP1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(Lbl_TH1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(Sp_Sl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addGap(29, 29, 29))
        );

        dialog_addProduct1.getContentPane().add(jPanel16, java.awt.BorderLayout.CENTER);

        setBackground(new java.awt.Color(204, 204, 255));
        setLayout(new java.awt.BorderLayout());

        addBtn_Bill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addBtn_Bill.setText("Thêm Hóa Đơn");
        addBtn_Bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtn_BillActionPerformed(evt);
            }
        });

        editBtn_Bill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editBtn_Bill.setText("Sửa Hóa Đơn");
        editBtn_Bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtn_BillActionPerformed(evt);
            }
        });

        delBtn_Bill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delBtn_Bill.setText("Xóa");
        delBtn_Bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtn_BillActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addBtn_Bill)
                .addGap(78, 78, 78)
                .addComponent(editBtn_Bill)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(delBtn_Bill)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delBtn_Bill)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addBtn_Bill)
                        .addComponent(editBtn_Bill)))
                .addGap(37, 37, 37))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý hóa đơn");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Mã Laptop", "Tên Laptop", "Tên Hãng", "Mã Khách Hàng", "Giá bán", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

//Sửa hóa đơn
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int quantity = Integer.parseInt(edit_quantitySp.getValue().toString());
        if (quantity<=0) {
            // Nếu có trường nào rỗng, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(null, "Vui lòng chọn số lượng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            
            String idLaptop= edit_idLaptopCB.getSelectedItem().toString();
            String idGuest= edit_idGuestCB.getSelectedItem().toString();
            Date date = bDAO.getBillDateById(edit_idBill.getText());
            Double money = quantity*pDAO.getProductPriceById(idLaptop);
            Bill b  = new Bill(edit_idBill.getText(),idGuest,date,money,quantity);
            bDAO.updateBill(b);
            setTable();
            JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            edit_dialog.dispose();
            
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void edit_idLaptopCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_idLaptopCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_idLaptopCBActionPerformed
//Mở cửa sổ thêm hóa đơn
    private void addBtn_BillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtn_BillActionPerformed
        // TODO add your handling code here:
        add_dialog.setVisible(true);
        add_dialog.setSize(1200,600);
        add_idGuestCB.removeAllItems();
        
        for(Guest guest : listGuest){
            add_idGuestCB.addItem(guest.getID());
        }
        setTable_ADD();
        listAdd = new ArrayList<>();
    }//GEN-LAST:event_addBtn_BillActionPerformed
// Mở cửa sổ sửa hóa đơn
    private void editBtn_BillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtn_BillActionPerformed
//        // TODO add your handling code here:
//        if(jTable1.getSelectedRow()<0){
//            JOptionPane.showMessageDialog(null, "Chọn Sản Phẩm cần sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }else{
//        edit_dialog.setVisible(true);
//        edit_dialog.setSize(600,500);
//        edit_idBill.setText(listBill.get(jTable1.getSelectedRow()).getID());
//        edit_idGuestCB.removeAllItems();
//        edit_idLaptopCB.removeAllItems();
//        for(Product laptop : listLaptop){
//            edit_idLaptopCB.addItem(laptop.getID());
//        }
//        for(Guest guest : listGuest){
//            edit_idGuestCB.addItem(guest.getID());
//        }
////        edit_idLaptopCB.addItem(listBill.get(jTable1.getSelectedRow()).getIdLaptop());
//        edit_idLaptopCB.setSelectedItem(listBill.get(jTable1.getSelectedRow()).getIdLaptop());
////        edit_idGuestCB.addItem(listBill.get(jTable1.getSelectedRow()).getIdGuest());
//        edit_idGuestCB.setSelectedItem(listBill.get(jTable1.getSelectedRow()).getIdGuest());
//        edit_quantitySp.setValue(listBill.get(jTable1.getSelectedRow()).getQuantity());
//        }

        // Sửa mới
        edit_newDialog.setVisible(true);
        edit_newDialog.setSize(1200,600);
        int selectedRow = jTable1.getSelectedRow();
        if (  selectedRow >-1) { // Kiểm tra nếu có dòng nào được chọn
    // Lấy giá trị từ các cột của dòng đang được chọn (giả sử có 3 cột)
    String idBill = jTable1.getValueAt(selectedRow, 1).toString(); // Cột 0
    id =idBill;
    String idGuest = jTable1.getValueAt(selectedRow, 2).toString(); // Cột 1
    String date = jTable1.getValueAt(selectedRow, 3).toString(); // Cột 2
    Double price = Double.parseDouble(jTable1.getValueAt(selectedRow, 4).toString()); // Cột 3
    int quantity = Integer.parseInt(jTable1.getValueAt(selectedRow, 5).toString()); // Cột 4
    edit_MHD.setText(idBill);
    edit_TongTien.setText(price.toString());
    edit_TongSL.setText(String.valueOf(quantity));
    edit_idGuestCB1.removeAllItems();
    edit_idGuestCB1.addItem(idGuest);
    edit_idGuestCB1.setSelectedItem(idGuest);
        for(Guest guest : listGuest){
            edit_idGuestCB1.addItem(guest.getID());
        }
       
        setTable_AddProduct();
        list1 = new ArrayList<Product>();
        listDB = dbDAO.getDetailBillsByIdBill(idBill);
        setTable_Edit(idBill);
        
        
        
        }
      
    }//GEN-LAST:event_editBtn_BillActionPerformed

    private void delBtn_BillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtn_BillActionPerformed
        // TODO add your handling code here:
        if(jTable1.getSelectedRow()<0){
            JOptionPane.showMessageDialog(null, "Chọn Sản Phẩm cần sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }else{
            bDAO.deleteBill(listBill.get(jTable1.getSelectedRow()).getID());
            JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            setTable();
        }
    }//GEN-LAST:event_delBtn_BillActionPerformed
// Thay đổi tổng tiền theo số lượng - edit
    private void edit_quantitySpStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_edit_quantitySpStateChanged
        // TODO add your handling code here:
        edit_sumPrice.setText("Tổng tiền: "+(int)edit_quantitySp.getValue()*pDAO.getProductPriceById(edit_idLaptopCB.getSelectedItem().toString()));
    }//GEN-LAST:event_edit_quantitySpStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        add_dialog.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void add_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_idActionPerformed
//Nút xác nhận thêm sản phẩm
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int quantity = (int)Sp_Sl.getValue();
        Double money = quantity*pDAO.getProductPriceById(Lbl_MSP.getText());
        DetailBill detailBill = new DetailBill(null,Lbl_MSP.getText() , null,money , quantity);
        listAdd.add(detailBill);
        TongTien.setText(String.valueOf((Double.parseDouble(TongTien.getText())+money)));
        tongSoLuong.setText(String.valueOf(Integer.parseInt(tongSoLuong.getText())+quantity));
        dialog_addProduct.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String idBill = add_id.getText();
        String idGuest = add_idGuestCB.getSelectedItem().toString();
        for( DetailBill db : listAdd){
            db.setIdBill(idBill);
            db.setIdGuest(idGuest);
            dbDAO.insertDetailBill(db);
        }
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(today);
        Bill b = new Bill(idBill, idGuest, today, Double.valueOf(TongTien.getText()), Integer.parseInt(tongSoLuong.getText()));
        bDAO.addBill(b);
        JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        add_dialog.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dialog_addProduct.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        edit_newDialog.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        int quantity = (int)Sp_Sl1.getValue();
        Double money = quantity*pDAO.getProductPriceById(Lbl_MSP1.getText());
        Product p = new Product( Lbl_MSP1.getText(),Lbl_TSP1.getText() ,Lbl_TH1.getText(),money, quantity);
        list1.add(p);
        dialog_addProduct1.dispose();
        setTable_Edit(id);
        edit_TongTien.setText(String.valueOf(money+Double.parseDouble(edit_TongTien.getText())));
        edit_TongSL.setText(String.valueOf(quantity+Integer.parseInt(edit_TongSL.getText())));
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed
//Button Sửa hóa đơn
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        dbDAO.deleteDetailBill(id);
        bDAO.deleteBill(id);
        for ( Product p : list1){
            DetailBill d = new DetailBill(id, p.getID() , edit_idGuestCB1.getSelectedItem().toString(),p.getPrice() , p.getQuantity());
            listDB.add(d);
        }
        for(DetailBill d: listDB){
            dbDAO.insertDetailBill(d);
        }
        int quantity = dbDAO.getTotalByIdBill(id).getQuantity();
        Double money = dbDAO.getTotalByIdBill(id).getPrice();
        
        
        Date date= (Date)jTable1.getValueAt(jTable1.getSelectedRow(), 3);
        Bill b = new Bill(id,edit_idGuestCB1.getSelectedItem().toString() , date ,money,quantity );
        bDAO.addBill(b);
        JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        edit_newDialog.dispose();
        
        
    }//GEN-LAST:event_jButton9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lbl_MSP;
    private javax.swing.JLabel Lbl_MSP1;
    private javax.swing.JLabel Lbl_TH;
    private javax.swing.JLabel Lbl_TH1;
    private javax.swing.JLabel Lbl_TSP;
    private javax.swing.JLabel Lbl_TSP1;
    private javax.swing.JSpinner Sp_Sl;
    private javax.swing.JSpinner Sp_Sl1;
    private javax.swing.JLabel TongTien;
    private javax.swing.JButton addBtn_Bill;
    private javax.swing.JLabel add_brandlb;
    private javax.swing.JLabel add_brandlb1;
    private javax.swing.JDialog add_dialog;
    private javax.swing.JTextField add_id;
    private javax.swing.JComboBox<String> add_idGuestCB;
    private javax.swing.JLabel add_idlb;
    private javax.swing.JLabel add_idlb1;
    private javax.swing.JLabel add_pricelb1;
    private javax.swing.JLabel add_pricelb2;
    private javax.swing.JLabel add_sumPrice;
    private javax.swing.JLabel add_sumPrice1;
    private javax.swing.JTable add_table;
    private javax.swing.JButton delBtn_Bill;
    private javax.swing.JDialog dialog_addProduct;
    private javax.swing.JDialog dialog_addProduct1;
    private javax.swing.JButton editBtn_Bill;
    private javax.swing.JLabel edit_MHD;
    private javax.swing.JTable edit_ProductTable;
    private javax.swing.JLabel edit_TongSL;
    private javax.swing.JLabel edit_TongTien;
    private javax.swing.JDialog edit_dialog;
    private javax.swing.JLabel edit_idBill;
    private javax.swing.JComboBox<String> edit_idGuestCB;
    private javax.swing.JComboBox<String> edit_idGuestCB1;
    private javax.swing.JComboBox<String> edit_idLaptopCB;
    private javax.swing.JDialog edit_newDialog;
    private javax.swing.JSpinner edit_quantitySp;
    private javax.swing.JLabel edit_sumPrice;
    private javax.swing.JTable edit_table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel tongSoLuong;
    // End of variables declaration//GEN-END:variables
}
