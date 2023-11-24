/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client;

import Model.Account;
import javax.swing.table.TableColumn;
import Controller.OrderController;
import static Controller.OrderController.IDTABLE;
import DAOInterface.TableInterface;
import java.rmi.Naming;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class OrderServer extends javax.swing.JFrame  {

//    int IDTABLE = -1;
    private Account loginAccount;

    public Account getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(Account account) {
        loginAccount = account;
        OrderController.changeAccount(loginAccount.getType(), jMenu_Admin);
    }

    /**
     * Creates new form OrderFrame
     */
    
    public void LoadAgaint() {
        OrderController.LoadTableServer(jPanel_or_table, jTable1, jLabel_Tien);
        OrderController.loadFoodCategory(jComboBox_Category, jComboBox2);
        OrderController.LoadFoodListByCategoryId(1, jComboBox2);
        OrderController.loadTableFood(jComboBox_Chuyenban_p5);
        OrderController.showBill(jTable1, jLabel_Tien);
        
    }

    public OrderServer(Account acc) {
        this.loginAccount = acc;
        initComponents();
        // Lấy cột thứ hai từ JTable
        TableColumn column = jTable1.getColumnModel().getColumn(0);
        // Đặt chiều rộng mong muốn cho cột thứ hai
        column.setPreferredWidth(180);
        OrderController.changeAccount(acc.getType(), jMenu_Admin);
        setTitle("Phần mềm quản lý quán cafe");
        setLocationRelativeTo(null);
        OrderController.LoadTableServer(jPanel_or_table, jTable1, jLabel_Tien);
        OrderController.loadFoodCategory(jComboBox_Category, jComboBox2);
        OrderController.LoadFoodListByCategoryId(1, jComboBox2);
        OrderController.loadTableFood(jComboBox_Chuyenban_p5);
    }

//    public void changeAccount(int type) {
//        jMenu_Admin.setEnabled(type == 1);
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel_or_table = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jComboBox_Category = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton_Themmon_p3 = new javax.swing.JButton();
        jSpinner_Count = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel_Tongtien = new javax.swing.JLabel();
        jLabel_Tien = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton_Thanhtoan_p5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_Chuyenban_p5 = new javax.swing.JComboBox<>();
        jSpinner_Giamgia_p5 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_Admin = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu_Thongtintaikhoan = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(153, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_or_table.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_or_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_or_table.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_or_table.setAutoscrolls(true);
        jPanel_or_table.setName(""); // NOI18N
        jPanel2.add(jPanel_or_table, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 630));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 450, 630));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox_Category.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CategoryActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton_Themmon_p3.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_Themmon_p3.setForeground(new java.awt.Color(51, 51, 255));
        jButton_Themmon_p3.setText("Thêm món");
        jButton_Themmon_p3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Themmon_p3ActionPerformed(evt);
            }
        });

        jSpinner_Count.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jSpinner_Count.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSpinner_Count.setValue(1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_Category, 0, 229, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Themmon_p3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner_Count, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox_Category, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Themmon_p3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner_Count, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 440, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên món", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jScrollPane2.setViewportView(jTable1);

        jLabel_Tongtien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_Tongtien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Tongtien.setText("Tổng tiền:");

        jLabel_Tien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_Tien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Tien.setText(" 0,0  VNĐ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel_Tongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_Tien, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Tongtien, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jLabel_Tien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 440, 410));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton_Thanhtoan_p5.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_Thanhtoan_p5.setForeground(new java.awt.Color(51, 51, 255));
        jButton_Thanhtoan_p5.setText("Thanh toán");
        jButton_Thanhtoan_p5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Thanhtoan_p5ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Giảm giá");

        jComboBox_Chuyenban_p5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Chuyenban_p5ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setText("Chuyển bàn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jComboBox_Chuyenban_p5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSpinner_Giamgia_p5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton_Thanhtoan_p5)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner_Giamgia_p5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Chuyenban_p5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Thanhtoan_p5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, -1, -1));

        jMenu_Admin.setText("Admin");
        jMenu_Admin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenu_Admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_AdminActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Quản lý");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu_Admin.add(jMenuItem1);

        jMenuBar1.add(jMenu_Admin);

        jMenu_Thongtintaikhoan.setText("About");
        jMenu_Thongtintaikhoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jMenuItem2.setText("Thông tin tài khoản");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu_Thongtintaikhoan.add(jMenuItem2);

        jMenuItem3.setText("Đăng xuất");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu_Thongtintaikhoan.add(jMenuItem3);

        jMenuBar1.add(jMenu_Thongtintaikhoan);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Nút thông tin tài khoản
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        InformationServer info = new InformationServer(loginAccount);
        info.setVisible(true);
        isShowing();
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    // nút thêm món
    private void jButton_Themmon_p3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Themmon_p3ActionPerformed
        OrderController.InsertFood(jComboBox2, jSpinner_Count);
        OrderController.showBill(jTable1, jLabel_Tien);
        OrderController.LoadTableServer(jPanel_or_table, jTable1, jLabel_Tien);
    }//GEN-LAST:event_jButton_Themmon_p3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox_CategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CategoryActionPerformed

    }//GEN-LAST:event_jComboBox_CategoryActionPerformed

    private void jButton_Thanhtoan_p5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Thanhtoan_p5ActionPerformed
        int discount = (int) jSpinner_Giamgia_p5.getValue();
        String label = jLabel_Tien.getText();
        double totalPrice = 0;
//        OrderController.InLichsugiaodich(discount);
        OrderController.payBill(jSpinner_Giamgia_p5, discount, label, totalPrice);
        OrderController.showBill(jTable1, jLabel_Tien);
        OrderController.LoadTableServer(jPanel_or_table, jTable1, jLabel_Tien);
        jLabel_Tien.setText("0  VNĐ");
    }//GEN-LAST:event_jButton_Thanhtoan_p5ActionPerformed

    private void jComboBox_Chuyenban_p5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Chuyenban_p5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_Chuyenban_p5ActionPerformed
    // Nút đăng xuất 
    public AdminServer admin;
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        LoginServer login = new LoginServer();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu_AdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_AdminActionPerformed

    }//GEN-LAST:event_jMenu_AdminActionPerformed
    // Nút admin
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        admin = new AdminServer();
        admin.setVisible(true);
        this.isShowing();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int id1 = IDTABLE;
        String selectedItem = jComboBox_Chuyenban_p5.getSelectedItem().toString();
        int id2 = Integer.valueOf(selectedItem.substring(jComboBox_Chuyenban_p5.getSelectedItem().toString().indexOf(" ") + 1));
        System.out.println("Chuyển bàn: " + id1 + " với " + id2);
        SwitchTable(id1, id2);
//        OrderController.LoadTableServer(jPanel_or_table, jTable1, jLabel_Tien);
        OrderController.showBill(jTable1, jLabel_Tien);
        OrderController.LoadTableServer(jPanel_or_table, jTable1, jLabel_Tien);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(OrderServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(OrderServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(OrderServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(OrderServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new OrderServer().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Thanhtoan_p5;
    private javax.swing.JButton jButton_Themmon_p3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox_Category;
    private javax.swing.JComboBox<String> jComboBox_Chuyenban_p5;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel_Tien;
    private javax.swing.JLabel jLabel_Tongtien;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenu jMenu_Admin;
    private javax.swing.JMenu jMenu_Thongtintaikhoan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel_or_table;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner_Count;
    private javax.swing.JSpinner jSpinner_Giamgia_p5;
    public javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    public void SwitchTable(int id1, int id2) {
        try {
            int check = JOptionPane.showConfirmDialog(null, String.format("Bạn chắc chắn muốn đổi từ bàn " + id1 + " sang bàn " + id2 + " ?"), "Thông  báo", JOptionPane.YES_OPTION);
            if (check == JOptionPane.OK_OPTION) {
                TableInterface tableRmi = (TableInterface) Naming.lookup("rmi://localhost:2004/Table");
                tableRmi.SwitchTable(id1, id2);

                JOptionPane.showConfirmDialog(null, "Chuyển bàn thành công!", "Thông  báo", JOptionPane.OK_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Chuyển bàn thất bại!", "Thông  báo", JOptionPane.YES_OPTION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
