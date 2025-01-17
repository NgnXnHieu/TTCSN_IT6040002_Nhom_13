/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import dao.StaffDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Staff;
import model.User;

/**
 *
 * @author abc
 */
public class StaffPanel extends javax.swing.JPanel {

    private UserDAO uDAO= new UserDAO();
    private List<User> listUser;
    private StaffDAO sdao = new StaffDAO();
    /**
     * Creates new form Staff
     */
    
    private void setTable() {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jTable1.setModel(defaultTableModel);
        ListSelectionModel listSelectionModel = jTable1.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Họ và tên");
        defaultTableModel.addColumn("Tên đăng nhập");
        defaultTableModel.addColumn("Vai trò");
        defaultTableModel.addColumn("Trạng thái hoạt động");
        listUser = uDAO.getAllUsers();

        // Duyệt qua danh sách products và thêm vào bảng
        int stt = 1; // Biến đánh số thứ tự
        for (User u : listUser) {
            Object[] row = new Object[6];
            row[0] = stt++;  // Số thứ tự
            row[1] = sdao.getStaffByIdUser(uDAO.getIdByUserName(u.getUserName())).getName();
            row[2] = u.getUserName();
            if(u.isRole()==true) row[3] = "Admin";
            else row[3] = "Nhân viên";
            if(u.isStatus()) row[4] = "Đang hoạt động";
            else row[4] = "Dừng hoạt động";
            
           
            defaultTableModel.addRow(row); // Thêm hàng vào bảng
        }

    }
    public StaffPanel() {
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
        add_quantitylb = new javax.swing.JLabel();
        add_namelb = new javax.swing.JLabel();
        add_brandlb = new javax.swing.JLabel();
        add_pricelb = new javax.swing.JLabel();
        add_nameText = new javax.swing.JTextField();
        add_usernameText = new javax.swing.JTextField();
        add_passwordText = new javax.swing.JTextField();
        add_checkpasswordText = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        add_roleCB = new javax.swing.JComboBox<>();
        edit_dialog = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        edit_nameText = new javax.swing.JTextField();
        edit_passwordText = new javax.swing.JTextField();
        edit_usernameLable = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        edit_roleCB = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        edit_checkpasswordText = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        edit_roleCB1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        add_dialog.setMinimumSize(new java.awt.Dimension(600, 400));
        add_dialog.getContentPane().setLayout(new javax.swing.BoxLayout(add_dialog.getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thêm tài khoản");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
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

        add_idlb.setText("Tên nhân viên");
        jPanel5.add(add_idlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        add_quantitylb.setText("Vai trò");
        jPanel5.add(add_quantitylb, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        add_namelb.setText("Tên đăng nhập");
        jPanel5.add(add_namelb, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        add_brandlb.setText("Mật khẩu");
        jPanel5.add(add_brandlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        add_pricelb.setText("Nhập lại mật khẩu");
        jPanel5.add(add_pricelb, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        add_nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_nameTextActionPerformed(evt);
            }
        });
        jPanel5.add(add_nameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 190, -1));

        add_usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_usernameTextActionPerformed(evt);
            }
        });
        jPanel5.add(add_usernameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 190, -1));

        add_passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_passwordTextActionPerformed(evt);
            }
        });
        jPanel5.add(add_passwordText, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 190, -1));

        add_checkpasswordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_checkpasswordTextActionPerformed(evt);
            }
        });
        jPanel5.add(add_checkpasswordText, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 190, -1));

        jButton4.setText("Hủy");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        jButton5.setText("Thêm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        add_roleCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Admin" }));
        add_roleCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_roleCBActionPerformed(evt);
            }
        });
        jPanel5.add(add_roleCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 190, -1));

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        add_dialog.getContentPane().add(jPanel4);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Sửa tài khoản");

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

        jLabel9.setText("Tên nhân viên");

        jLabel10.setText("Tên đăng nhập");

        jLabel11.setText("Mật khẩu");

        jLabel12.setText("Vai trò");

        edit_nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_nameTextActionPerformed(evt);
            }
        });

        edit_passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_passwordTextActionPerformed(evt);
            }
        });

        edit_usernameLable.setText("jLabel14");

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

        edit_roleCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Admin" }));
        edit_roleCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_roleCBActionPerformed(evt);
            }
        });

        jLabel13.setText("Xác nhận mật khẩu");

        edit_checkpasswordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_checkpasswordTextActionPerformed(evt);
            }
        });

        jLabel14.setText("Trạng thái");

        edit_roleCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Dừng hoạt động" }));

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
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edit_passwordText, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(edit_nameText, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(edit_usernameLable, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(edit_roleCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edit_checkpasswordText, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(edit_roleCB1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(edit_nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(edit_usernameLable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(edit_passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(edit_checkpasswordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(edit_roleCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(edit_roleCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        edit_dialog.getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(40, 40, 40))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jButton1.setText("Tạo Nhân Viên");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(64, 64, 64)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(29, 29, 29))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Nhân Viên", "Tên Nhân Viên", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void add_nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_nameTextActionPerformed

    private void add_usernameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_usernameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_usernameTextActionPerformed

    private void add_passwordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_passwordTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_passwordTextActionPerformed

    private void add_checkpasswordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_checkpasswordTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_checkpasswordTextActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        add_dialog.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (add_nameText.getText().isEmpty() || add_usernameText.getText().isEmpty() || add_passwordText.getText().isEmpty()|| add_checkpasswordText.getText().isEmpty()) {
            // Nếu có trường nào rỗng, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            // Nếu tất cả các trường đều có dữ liệu
            ArrayList<String> usernamelist = new ArrayList<>();
            for(User u : listUser){
                usernamelist.add(u.getUserName());
            }
            // Kiểm tra nếu ID đã tồn tại
            if (usernamelist.contains(add_usernameText.getText())) {
                // Hiển thị thông báo lỗi nếu ID trùng
                JOptionPane.showMessageDialog(null, "Username đã tồn tại! Vui lòng nhập Username khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                // Nếu ID không trùng, thực hiện hành động khác (ví dụ: thêm ID mới)
                JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                User u= null;
                if(add_roleCB.getSelectedIndex()==1){
                    u = new User(add_usernameText.getText(), add_passwordText.getText(),true,true );
                    
                }else if (add_roleCB.getSelectedIndex()==0){
                    u = new User(add_usernameText.getText(), add_passwordText.getText(),false,true );
                }
                String iduser = uDAO.getIdByUserName(add_usernameText.getText());
                Staff s = new Staff(add_nameText.getText(), "", "",iduser );
                uDAO.addUser(u);
                sdao.addStaff(s);
                add_dialog.dispose();
                setTable();
            }

        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void edit_nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_nameTextActionPerformed

    private void edit_passwordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_passwordTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_passwordTextActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (edit_nameText.getText().isEmpty() || edit_passwordText.getText().isEmpty()||edit_checkpasswordText.getText().isEmpty() ) {
            // Nếu có trường nào rỗng, hiển thị thông báo lỗi
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            boolean role= false;
            boolean status= false;
            if(edit_roleCB.getSelectedIndex()==0) role = false; else role = true;
            if(edit_roleCB1.getSelectedIndex()==0) status = true; else status = false;
            User u  = new User( edit_usernameLable.getText(), edit_passwordText.getText(),role, status);
            sdao.updateStaffNameByIdUser(uDAO.getIdByUserName(edit_usernameLable.getText()), edit_nameText.getText());
            uDAO.updateUser(u);
            edit_dialog.dispose();
            setTable();
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void add_roleCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_roleCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_roleCBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        add_dialog.setVisible(true);
        add_dialog.setSize(600,500);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void edit_checkpasswordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_checkpasswordTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_checkpasswordTextActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         if(jTable1.getSelectedRow()<0){
            JOptionPane.showMessageDialog(null, "Chọn Sản Phẩm cần sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }else{
            uDAO.deleteUser(listUser.get(jTable1.getSelectedRow()).getUserName());
            JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            setTable();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         if(jTable1.getSelectedRow()<0){
            JOptionPane.showMessageDialog(null, "Chọn Sản Phẩm cần sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }else{
        edit_dialog.setVisible(true);
        edit_dialog.setSize(600,500);
        edit_usernameLable.setText(listUser.get(jTable1.getSelectedRow()).getUserName());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void edit_roleCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_roleCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_roleCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel add_brandlb;
    private javax.swing.JTextField add_checkpasswordText;
    private javax.swing.JDialog add_dialog;
    private javax.swing.JLabel add_idlb;
    private javax.swing.JTextField add_nameText;
    private javax.swing.JLabel add_namelb;
    private javax.swing.JTextField add_passwordText;
    private javax.swing.JLabel add_pricelb;
    private javax.swing.JLabel add_quantitylb;
    private javax.swing.JComboBox<String> add_roleCB;
    private javax.swing.JTextField add_usernameText;
    private javax.swing.JTextField edit_checkpasswordText;
    private javax.swing.JDialog edit_dialog;
    private javax.swing.JTextField edit_nameText;
    private javax.swing.JTextField edit_passwordText;
    private javax.swing.JComboBox<String> edit_roleCB;
    private javax.swing.JComboBox<String> edit_roleCB1;
    private javax.swing.JLabel edit_usernameLable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
