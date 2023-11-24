/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import DAOInterface.BillInfoInterface;
import DAOInterface.BillInterface;
import DAOInterface.FoodCategoryInterface;
import DAOInterface.FoodInterface;
import DAOInterface.TableInterface;
import Model.Food;
import Model.FoodCategory;
import Model.Pay;
import Model.TableFood;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import java.rmi.Naming;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Hữu Tiến
 */
public class OrderController {
//    public static JTable jTable1;

    public static int IDTABLE = -1;

    public static void LoadTable(JPanel panel) {
        try {
            TableInterface tableRmi = (TableInterface) Naming.lookup("rmi://localhost:2004/Table");
            List<TableFood> tableList = tableRmi.LoadTableList();
            for (TableFood item : tableList) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(110, 80));
                btn.setText(item.getName() + ":" + item.getStatus());
                if (item.getStatus().equals("Trống")) {
                    btn.setBackground(Color.cyan);
                } else {
                    btn.setBackground(Color.pink);
                }

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int tableID = ((TableFood) btn.getClientProperty("TableFood")).getId();
                        System.out.println("số bàn " + tableID);
                        String buttonText = btn.getText();
                        getIDTable(buttonText);
//                        OrderClient2 o2 = new OrderClient2();
//                        o2.setVisible(true);
                    }
                });
                btn.putClientProperty("TableFood", item);
                panel.add(btn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIDTable(String buttonText) {
        int startIndex = buttonText.indexOf("Bàn") + 3; // +3 để bỏ qua ký tự "Bàn" và khoảng trắng sau nó
        int endIndex = buttonText.indexOf(":");
        String tableIDString = buttonText.substring(startIndex, endIndex).trim();
        int tableID = Integer.parseInt(tableIDString);
        IDTABLE = tableID;
        return tableID;
    }

    //    ===================================================================================================================
    //    ===================================================================================================================
    //    ===================================================================================================================
    public static void showBill(JTable jTable1, JLabel jLabel_Tien) {
        try {
            BillInterface billRmi = (BillInterface) Naming.lookup("rmi://localhost:2004/Bill");
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            model1.setRowCount(0);
            DefaultTableModel model = new DefaultTableModel();
            List<Pay> billList = billRmi.LoadBillList(IDTABLE);

            double totalPrice = 0;
            model.addColumn("Tên món");
            model.addColumn("Số lượng");
            model.addColumn("Đơn giá");
            model.addColumn("Thành tiền");
            for (Pay pay : billList) {
                totalPrice += pay.getTotalPrice();
                model.addRow(new Object[]{
                    pay.getName(),
                    pay.getCount(),
                    pay.getPrice(),
                    pay.getTotalPrice()
                });
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String formattedNumber = numberFormat.format(totalPrice);
                System.out.println("Tổng tiền: " + totalPrice);
                jTable1.setModel(model);
                jLabel_Tien.setText(formattedNumber + " VNĐ");
                jTable1.setModel(model);
                // Lấy cột thứ hai từ JTable
                TableColumn column = jTable1.getColumnModel().getColumn(0);
                // Đặt chiều rộng mong muốn cho cột thứ hai
                column.setPreferredWidth(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadFoodCategory(JComboBox jComboBox_Category, JComboBox jComboBox2) {
        try {
            FoodCategoryInterface foodRmi = (FoodCategoryInterface) Naming.lookup("rmi://localhost:2004/FoodCategory");
            List<FoodCategory> foodList = foodRmi.GetListCategory();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
//        model.addElement("");
            for (FoodCategory item : foodList) {
                model.addElement(item.getId() + ": " + item.getName());
            }
            jComboBox_Category.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (jComboBox_Category.getSelectedItem().toString() == null) {
                        int CategoryID = -1;
//                    int CategoryID = 1;
                    } else {
                        int CategoryID = Integer.valueOf(jComboBox_Category.getSelectedItem().toString().split(":")[0].trim());
                        System.out.println("Id loại thức ăn:" + CategoryID);
                        LoadFoodListByCategoryId(CategoryID, jComboBox2);
                    }
                }
            });
            jComboBox_Category.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LoadFoodListByCategoryId(int id, JComboBox jComboBox2) {
        try {
            FoodInterface foodRmi = (FoodInterface) Naming.lookup("rmi://localhost:2004/Food");
            List<Food> listFood = foodRmi.GetFoodByCategoryID(id);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (Food item : listFood) {
                model.addElement(item.getId() + ": " + item.getName());
            }
            jComboBox2.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void InsertFood(JComboBox jComboBox2, JSpinner jSpinner_Count) {
        try {
            BillInterface billRmi = (BillInterface) Naming.lookup("rmi://localhost:2004/Bill");
            BillInfoInterface billInfoRmi = (BillInfoInterface) Naming.lookup("rmi://localhost:2004/BillInfo");

            System.out.println("IDTable ở nút thêm " + IDTABLE);
            int idBill = billRmi.getUncheckBillIDByTableID(IDTABLE);
            System.out.println("IdBill ở nút thêm: " + idBill);
            int idFood = Integer.parseInt(jComboBox2.getSelectedItem().toString().split(":")[0].trim());
            System.out.println("idFood ở nút thêm: " + idFood);
            int count = (int) jSpinner_Count.getValue();
            System.out.println("count ở nút thêm: " + count);
            if (idBill == -1) {
                System.out.println("bắt đầu insert Bill");
                billRmi.InsertBill(IDTABLE);
                System.out.println("Bắt đầu insert BillInfo");
                billInfoRmi.InsertBillInfo(billRmi.GetMaxIDBill(), idFood, count);
            } else {
                System.out.println("Bắt đầu cập nhật Bill");
                billInfoRmi.InsertBillInfo(idBill, idFood, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    ===================================================================================================================
    //    ===================================================================================================================
    //    ===================================================================================================================
    public static void changeAccount(int type, JMenu jMenu_Admin) {
        jMenu_Admin.setEnabled(type == 1);
    }

    public static void payBill(JSpinner jSpinner_Giamgia_p5, int discount, String label, double totalPrice) {
        try {
            BillInterface billRmi = (BillInterface) Naming.lookup("rmi://localhost:2004/Bill");
            int idBill = billRmi.getUncheckBillIDByTableID(IDTABLE);
            List<Pay> billList = billRmi.LoadBillList(IDTABLE);
            String formattedNumber = null;
            for (Pay pay : billList) {
                totalPrice += pay.getTotalPrice();
            }
            System.out.println("Tổng tiền: " + totalPrice);
            double total = totalPrice - ((totalPrice / 100.0) * (discount * 1.0));
            System.out.println("Sau khi tinh tiền " + total);
            System.out.println("Discount: " + discount);
            System.out.println("Số tiền đuợc giảm " + (totalPrice / 100.0) * (discount * 1.0));
            NumberFormat numberFormat = new DecimalFormat("###,###");
            formattedNumber = numberFormat.format(total);
            System.out.println("Số tiền sau khi giảm " + total);
            if (idBill != -1) {
                int ref = JOptionPane.showConfirmDialog(null,
                        "Số tiền cần thanh toán là: "
                        + formattedNumber + " VNĐ \n"
                        + "Bạn có chắc muốn thanh toán hóa đơn bàn "
                        + IDTABLE,
                        "Thông báo",
                        JOptionPane.YES_NO_OPTION);
                if (ref == JOptionPane.YES_OPTION) {
                    InLichsugiaodich(discount);
                    InHoadon(discount);
                    billRmi.CheckOut(IDTABLE, discount, total);
                    System.out.println("Check out");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LoadTableServer(JPanel jPanel_or_table, JTable jTable1, JLabel jLabel_Tien) {
        try {
            jPanel_or_table.removeAll();
            TableInterface tableRmi = (TableInterface) Naming.lookup("rmi://localhost:2004/Table");
            List<TableFood> tableList = tableRmi.LoadTableList();
            for (TableFood item : tableList) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(120, 90));
                btn.setText(item.getName() + ":" + item.getStatus());
                if (item.getStatus().equals("Trống")) {
                    btn.setBackground(Color.cyan);
                } else {
                    btn.setBackground(Color.pink);
                }

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int tableID = ((TableFood) btn.getClientProperty("TableFood")).getId();
                        System.out.println("số bàn " + tableID);
                        String buttonText = btn.getText();
                        getIDTable(buttonText);
                        showBillServer(jTable1, jLabel_Tien);
                    }
                });
                btn.putClientProperty("TableFood", item);
                jPanel_or_table.add(btn);
            }
            jPanel_or_table.revalidate(); // Cập nhật giao diện
            jPanel_or_table.repaint(); // Vẽ lại giao diện

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showBillServer(JTable jTable1, JLabel jLabel_Tien) {
        jLabel_Tien.setText("0  VNĐ");
        try {
            BillInterface billRmi = (BillInterface) Naming.lookup("rmi://localhost:2004/Bill");
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            model1.setRowCount(0);
            DefaultTableModel model = new DefaultTableModel();
            List<Pay> billList = billRmi.LoadBillList(IDTABLE);

            double totalPrice = 0;
            model.addColumn("Tên món");
            model.addColumn("Số lượng");
            model.addColumn("Đơn giá");
            model.addColumn("Thành tiền");
            for (Pay pay : billList) {
                totalPrice += pay.getTotalPrice();
                model.addRow(new Object[]{
                    pay.getName(),
                    pay.getCount(),
                    pay.getPrice(),
                    pay.getTotalPrice()
                });
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String formattedNumber = numberFormat.format(totalPrice);
                System.out.println("Tổng tiền: " + totalPrice);
                jTable1.setModel(model);
                jLabel_Tien.setText(formattedNumber + " VNĐ");
                // Lấy cột thứ hai từ JTable
                TableColumn column = jTable1.getColumnModel().getColumn(0);
                // Đặt chiều rộng mong muốn cho cột thứ hai
                column.setPreferredWidth(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadTableFood(JComboBox jComboBox_Chuyenban_p5) {
        try {
            TableInterface tableRmi = (TableInterface) Naming.lookup("rmi://localhost:2004/Table");
            List<TableFood> listTable = tableRmi.LoadTableList();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (TableFood item : listTable) {
                model.addElement(item.getName());
            }
            jComboBox_Chuyenban_p5.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SwitchTable(int id1, int id2) {
        try {
            int check = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn đổi bàn!", "Thông  báo", JOptionPane.YES_OPTION);
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

    public static void InLichsugiaodich(int jSpiner) {
        int width = 20;
        String billInfo = String.format("%-" + width + "s", "Tên món")
                + String.format("%-" + width + "s", "Số lượng")
                + String.format("%-" + width + "s", "Đơn giá")
                + String.format("Thành tiền\n");

        String fileName = "C:\\Users\\ASUS\\OneDrive\\Máy tính\\Transaction history.txt";
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        String transactionDetails = "\t\t\t    CODEFAC QUÁN  "
                + "\n \t\t  Điện Phương, Điện Bàn, Quảng Nam\n"
                + "\n \t \t \t      HÓA ĐƠN  \t\n"
                + "\n  Bàn : " + IDTABLE
                + "\n  Giờ vào: " + formattedDateTime
                + "\n  Giờ ra: " + formattedDateTime
                + "\n------------------------------------------------------------------------\n";

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(transactionDetails);
            writer.write(billInfo);
            try {

                String formattedNumber = "";
                BillInterface billRmi = (BillInterface) Naming.lookup("rmi://localhost:2004/Bill");
                List<Pay> billList = billRmi.LoadBillList(IDTABLE);

                double totalPrice = 0;
                int totalWidth = 20; // Tổng độ rộng căn lề
                String format = "%-" + totalWidth + "s";
                StringBuilder builder = new StringBuilder();

                for (Pay pay : billList) {
                    totalPrice += pay.getTotalPrice();
                    System.out.println(pay.toString());

                    String name = String.format(format, pay.getName());
                    String count = String.format(format, pay.getCount());
                    String price = String.format(format, pay.getPrice());
                    String totalPriceStr = String.format(format, pay.getTotalPrice());

                    builder.append(name);
                    builder.append(count);
                    builder.append(price);
                    builder.append(totalPriceStr);
                    builder.append(System.lineSeparator());
                }

                String bill = builder.toString();
                writer.write(bill);

                NumberFormat numberFormat = new DecimalFormat("###,###");
                formattedNumber = numberFormat.format(totalPrice);

                String total = "\n            ----------- \t----------- \t-----------\n"
                        + "\t\t\t\tGiảm giá: " + jSpiner + " % "
                        + "\t  TỔNG TIỀN: " + formattedNumber
                        + "  VNĐ."
                        + "\n--------------------------------------------------------------------------\n\n\n";
                writer.write(total);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void InHoadon(int jSpiner) {
        int width = 20;
        String billInfo = String.format("%-" + width + "s", "Tên món")
                + String.format("%-" + width + "s", "Số lượng")
                + String.format("%-" + width + "s", "Đơn giá")
                + String.format("Thành tiền\n");

        String fileName = "C:\\Users\\ASUS\\OneDrive\\Máy tính\\Bill.txt";

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        LocalDateTime previousDateTime = dateTime.minusSeconds(23);
        String formattedPreviousDateTime = previousDateTime.format(formatter);
        String transactionDetails = "\t\t\t    CODEFAC QUÁN  "
                + "\n \t\t  Điện Phương, Điện Bàn, Quảng Nam\n"
                + "\n \t \t \t      HÓA ĐƠN  \t\n"
                + "\n  Bàn : " + IDTABLE
                + "\n  Giờ vào: " + formattedPreviousDateTime
                + "\n  Giờ ra : " + formattedDateTime
                + "\n------------------------------------------------------------------------\n";

        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(transactionDetails);
            writer.write(billInfo);
            try {

                String formattedNumber = "";
                BillInterface billRmi = (BillInterface) Naming.lookup("rmi://localhost:2004/Bill");
                List<Pay> billList = billRmi.LoadBillList(IDTABLE);

                double totalPrice = 0;
                int totalWidth = 20; // Tổng độ rộng căn lề
                String format = "%-" + totalWidth + "s";
                StringBuilder builder = new StringBuilder();

                for (Pay pay : billList) {
                    totalPrice += pay.getTotalPrice();
                    System.out.println(pay.toString());

                    String name = String.format(format, pay.getName());
                    String count = String.format(format, pay.getCount());
                    String price = String.format(format, pay.getPrice());
                    String totalPriceStr = String.format(format, pay.getTotalPrice());

                    builder.append(name);
                    builder.append(count);
                    builder.append(price);
                    builder.append(totalPriceStr);
                    builder.append(System.lineSeparator());
                }

                String bill = builder.toString();
                writer.write(bill);

                NumberFormat numberFormat = new DecimalFormat("###,###");
                formattedNumber = numberFormat.format(totalPrice);

                String total = "\n            ----------- \t----------- \t-----------\n"
                        + "\t\t\t\tGiảm giá: " + jSpiner + " % "
                        + "\t  TỔNG TIỀN: " + formattedNumber
                        + "  VNĐ."
                        + "\n--------------------------------------------------------------------------\n\n\n";
                writer.write(total);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
