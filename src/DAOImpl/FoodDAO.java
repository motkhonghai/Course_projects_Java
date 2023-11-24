
package DAOImpl;

import DAOInterface.FoodInterface;
import JDBC.JDBCUtil;
import Model.Food;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class FoodDAO extends UnicastRemoteObject implements FoodInterface{

    public FoodDAO() throws RemoteException{
        
    }
    @Override
    public List<Food> GetFoodByCategoryID(int ID) {
        List<Food> list = new ArrayList<>();
        String query = "SELECT * FROM food WHERE IdCategory = ?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, ID);
            System.out.println(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int idCategory = rs.getInt("IdCategory");
                float price = rs.getFloat("Price");
                Food food = new Food(id, name, idCategory, price);
                System.out.println(food.toString());
                list.add(food);
            }
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Food> GetListFood() {
        List<Food> listFood = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM food ";
            PreparedStatement pst = conn.prepareCall(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int idCategory = rs.getInt(3);
                float price = rs.getFloat(4);
                Food food = new Food(id, name, idCategory, price);
                System.out.println(food.toString());
                listFood.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFood;
    }
    @Override
    public boolean GetInsertFood(String name, int idCategory, double price) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "INSERT INTO food(Id, Name, IdCategory, Price)\n"
                    + "SELECT COALESCE(MAX(Id), 0) + 1, \'" + name + "\', ?, ? FROM food";
            PreparedStatement pst = conn.prepareCall(query);
//            pst.setString(1, name);
            System.out.println(query);
            pst.setInt(1, idCategory);
            pst.setDouble(2, price);
            ref = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ref > 0;
    }
    @Override
    public boolean GetDeleteFood(int idFood) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "DELETE FROM  food  WHERE Id = ?;";
            PreparedStatement pst = conn.prepareCall(query);
            System.out.println(query);
            pst.setInt(1, idFood);
            ref = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ref > 0;
    }
    @Override
    public boolean GetUpdateFood(int idFood, String name, int idCategory, double price) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "UPDATE food SET Name=\'" + name + "\', IdCategory = ? ,Price = ?  WHERE Id = ?;";
            PreparedStatement pst = conn.prepareCall(query);
//            pst.setString(1, name);
            System.out.println(query);
            pst.setInt(1, idCategory);
            pst.setDouble(2, price);
            pst.setInt(3, idFood);
            ref = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ref > 0;
    }
    @Override
    public List<Food> SearchFoodByName(String Name) {
        List<Food> list = new ArrayList<>();
        String query = "SELECT * FROM food AS f \n WHERE LOWER(f.Name) LIKE '%" + Name + "%'";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement pst = conn.prepareCall(query);
//            pst.setString(1, name);
            System.out.println(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int idCategory = rs.getInt("IdCategory");
                float price = rs.getFloat("Price");
                Food food = new Food(id, name, idCategory, price);
                System.out.println(food.toString());
                list.add(food);
            }
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public boolean GetDeleteFoodByIdCategory(int idCategory) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "DELETE FROM  food  WHERE IdCategory = ?;";
            PreparedStatement pst = conn.prepareCall(query);
            System.out.println(query);
            pst.setInt(1, idCategory);
            ref = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
            if(ref > 0){
                System.out.println("Xóa Food by IdCategory thành công!");
            } else {
                System.out.println("Xóa Food by IdCategory thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ref > 0;
    }
}
