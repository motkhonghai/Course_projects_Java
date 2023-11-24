/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAOImpl.AccountDAO;
import DAOInterface.AccountInterface;
import DAOInterface.BillInfoInterface;
import DAOInterface.BillInterface;
import DAOInterface.FoodCategoryInterface;
import DAOInterface.FoodInterface;
import DAOInterface.TableInterface;
import Model.Account;
import Model.Food;
import Model.FoodCategory;
import Model.Statistic;
import Model.TableFood;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.rmi.Naming;
import java.sql.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Hữu Tiến
 */
public class AdminServerController {

    public static void ThongKe(Date checkIn, Date checkOut, JTable jTable_Thongke) {
        try {
            BillInterface billRmi = (BillInterface) Naming.lookup("rmi://localhost:2004/Bill");
            List<Statistic> list = billRmi.GetBillListByDate(checkIn, checkOut);
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Số bàn");
            model.addColumn("Giảm giá(%)");
            model.addColumn("Tổng tiền");
            model.addColumn("Ngày nhận bàn");
            model.addColumn("Ngày trả bàn");
            for (Statistic item : list) {
                System.out.println(item.toString());
                model.addRow(new Object[]{
                    item.getName(),
                    item.getDiscount(),
                    item.getTotalPrice(),
                    item.getCheckIn(),
                    item.getCheckOut()
                });
            }
            jTable_Thongke.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetInsertFood(String name, int idCategory, double price) {
        try {
            FoodInterface foodRmi = (FoodInterface) Naming.lookup("rmi://localhost:2004/Food");
            if (foodRmi.GetInsertFood(name, idCategory, price)) {
                JOptionPane.showConfirmDialog(null, "Thêm món thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Thêm món!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void GetDeleteFood(int idFood) {
        try {
            BillInfoInterface billInfRmi = (BillInfoInterface) Naming.lookup("rmi://localhost:2004/BillInfo");
            billInfRmi.GetDeleteBillInfoByIdFood(idFood);

            FoodInterface foodRmi = (FoodInterface) Naming.lookup("rmi://localhost:2004/Food");
            if (foodRmi.GetDeleteFood(idFood)) {
                JOptionPane.showConfirmDialog(null, "Xóa món thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Xóa món!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetUpdateFood(int idFood, String name, int idCategory, double price) {
        try {
            FoodInterface foodRmi = (FoodInterface) Naming.lookup("rmi://localhost:2004/Food");
            if (foodRmi.GetUpdateFood(idFood, name, idCategory, price)) {
                JOptionPane.showConfirmDialog(null, "Cập nhật món thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Cập nhật món!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LoadListFood(JTable jTable_Food) {
        try {
            FoodInterface foodRmi = (FoodInterface) Naming.lookup("rmi://localhost:2004/Food");
            List<Food> list = foodRmi.GetListFood();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Id");
            model.addColumn("Tên món");
            model.addColumn("Danh mục");
            model.addColumn("Giá");
            for (Food item : list) {
                System.out.println(item.toString());
                model.addRow(new Object[]{
                    item.getId(),
                    item.getName(),
                    item.getIdCategory(),
                    item.getPrice()
                });
                jTable_Food.setModel(model);
            }
            // Lấy cột thứ hai từ JTable
            TableColumn column = jTable_Food.getColumnModel().getColumn(1);

            // Đặt chiều rộng mong muốn cho cột thứ hai
            column.setPreferredWidth(200);  // Đặt chiều rộng là 150 pixel
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SearchFood(String Name, JTable jTable_Food) {
        try {
            FoodInterface foodRmi = (FoodInterface) Naming.lookup("rmi://localhost:2004/Food");
            List<Food> list = foodRmi.SearchFoodByName(Name);
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Id");
            model.addColumn("Tên món");
            model.addColumn("Danh mục");
            model.addColumn("Giá");
            System.out.println("Bắt đầu lấy giá trị");
            for (Food item : list) {
                model.addRow(new Object[]{
                    item.getId(),
                    item.getName(),
                    item.getIdCategory(),
                    item.getPrice()
                });

            }
            jTable_Food.setModel(model);
            // Lấy cột thứ hai từ JTable
            TableColumn column = jTable_Food.getColumnModel().getColumn(1);

            // Đặt chiều rộng mong muốn cho cột thứ hai
            column.setPreferredWidth(200);
        } catch (Exception e) {
        }
    }

    public static void LoadFoodCategoryByID(JComboBox cb, int id) {
        try {
            FoodCategoryInterface foodCateRmi = (FoodCategoryInterface) Naming.lookup("rmi://localhost:2004/FoodCategory");
            List<FoodCategory> foodList = foodCateRmi.GetListCategory();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            int selectedIndex = -1;

            for (int i = 0; i < foodList.size(); i++) {
                FoodCategory item = foodList.get(i);
                model.addElement(item.getId() + ": " + item.getName());

                if (item.getId() == id) {
                    selectedIndex = i;
                }
            }
            cb.setModel(model);
            cb.setSelectedIndex(selectedIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetInsertFoodCategory(String name) {
        try {
            FoodCategoryInterface foodCateRmi = (FoodCategoryInterface) Naming.lookup("rmi://localhost:2004/FoodCategory");
            if (foodCateRmi.GetInsertFoodCategory(name)) {
                JOptionPane.showConfirmDialog(null, "Thêm danh mục món thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi thêm danh mục món!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

    public static void GetListFoodCategory(JTable jTable_Danhmuc) {
        try {
            FoodCategoryInterface foodCateRmi = (FoodCategoryInterface) Naming.lookup("rmi://localhost:2004/FoodCategory");
            List<FoodCategory> list = foodCateRmi.GetListCategory();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Tên danh mục");
            for (FoodCategory item : list) {
                model.addRow(new Object[]{
                    item.getId(),
                    item.getName()
                });
            }
            jTable_Danhmuc.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetDeleteFoodCategory(int id) {
        try {
            FoodInterface foodRmi = (FoodInterface) Naming.lookup("rmi://localhost:2004/Food");
            foodRmi.GetDeleteFoodByIdCategory(id);

            FoodCategoryInterface foodCateRmi = (FoodCategoryInterface) Naming.lookup("rmi://localhost:2004/FoodCategory");
            if (foodCateRmi.GetDeleteFoodCategory(id)) {
                JOptionPane.showConfirmDialog(null, "Xóa danh mục món thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Xóa danh mục món!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetUpdateFoodCategory(int id, String name) {
        try {
            FoodCategoryInterface foodCateRmi = (FoodCategoryInterface) Naming.lookup("rmi://localhost:2004/FoodCategory");
            if (foodCateRmi.GetUpdateFoodCategory(id, name)) {
                JOptionPane.showConfirmDialog(null, "Sửa danh mục món thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Sửa danh mục món!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadTableFood(JTable jTable_Banan) {
        try {
            TableInterface tableRmi = (TableInterface) Naming.lookup("rmi://localhost:2004/Table");
            List<TableFood> list = tableRmi.LoadTableList();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Tên bàn");
            model.addColumn("Trạng thái");
            for (TableFood item : list) {
                model.addRow(new Object[]{
                    item.getId(),
                    item.getName(),
                    item.getStatus()
                });
            }
            jTable_Banan.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetInsertTable(String name, String status) {
        try {
            TableInterface tableRmi = (TableInterface) Naming.lookup("rmi://localhost:2004/Table");
            if (tableRmi.InserttableFood(name, status)) {
                JOptionPane.showConfirmDialog(null, "Thêm bàn thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi thêm bàn!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetDeleteTable(int id) {
        try {
            TableInterface tableRmi = (TableInterface) Naming.lookup("rmi://localhost:2004/Table");
            if (tableRmi.DeletetableFood(id)) {
                JOptionPane.showConfirmDialog(null, "Xóa bàn thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Xóa bàn!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetupdateTable(int id, String name, String status) {
        try {
            TableInterface tableRmi = (TableInterface) Naming.lookup("rmi://localhost:2004/Table");
            if (tableRmi.UpdatetableFood(id, name, status)) {
                JOptionPane.showConfirmDialog(null, "Cập nhật bàn thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Cập nhật bàn!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

    public static void GetInsertAcc(String user, int type) {
        try {
            AccountInterface accRmi = (AccountInterface) Naming.lookup("rmi://localhost:2004/Login");
            if (accRmi.InsertAcc(user, type)) {
                JOptionPane.showConfirmDialog(null, "Thêm tài khoản thành công!", "Thông báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Thêm Tài khoản!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LoadListAcc(JTable jTable_ad_tk) {
        try {
            AccountInterface accRmi = (AccountInterface) Naming.lookup("rmi://localhost:2004/Login");
            List<Account> list = accRmi.GetListAcc();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Tên tài khoản");
            model.addColumn("Loại");
            for (Account item : list) {
                System.out.println(item.toString());
                model.addRow(new Object[]{
                    item.getUserName(),
                    item.getType()
                });
                jTable_ad_tk.setModel(model);
            }
        } catch (Exception e) {
        }
    }

    public static void GetDeleteAcc(String user) {
        try {
            AccountInterface accRmi = (AccountInterface) Naming.lookup("rmi://localhost:2004/Login");
            if (accRmi.DeleteAcc(user)) {
                JOptionPane.showConfirmDialog(null, "Xóa tài khoản thành công!", "Thông  báo", JOptionPane.OK_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Xóa Tài khoản!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GetUpdateAcc(int type, String user) {
        try {
            AccountInterface accRmi = (AccountInterface) Naming.lookup("rmi://localhost:2004/Login");
            if (accRmi.UpdateAcc(type, user)) {
                JOptionPane.showConfirmDialog(null, "Cập nhật loại tài khoản thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Cập nhật loại Tài khoản!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ResetAcc(String user) {
        try {
            AccountInterface accRmi = (AccountInterface) Naming.lookup("rmi://localhost:2004/Login");
            if (accRmi.ResetAcc(user)) {
                JOptionPane.showConfirmDialog(null, "Reset tài khoản thành công!", "Thông  báo", JOptionPane.YES_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null, "Lỗi khi Reset Tài khoản!", "Thông  báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }

}
