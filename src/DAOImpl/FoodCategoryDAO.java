
package DAOImpl;

import DAOInterface.FoodCategoryInterface;
import JDBC.JDBCUtil;
import java.util.List;
import Model.FoodCategory;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class FoodCategoryDAO extends UnicastRemoteObject implements FoodCategoryInterface{

    public FoodCategoryDAO() throws RemoteException{
        
    }
    @Override
    public List<FoodCategory> GetListCategory() {
        List<FoodCategory> List = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM foodcategory ";
            PreparedStatement pst = conn.prepareCall(query);
            System.out.println(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                FoodCategory fc = new FoodCategory(id, name);
                System.out.println(fc.toString());
                List.add(fc);
            }
        } catch (Exception e) {
        }
        return List;
    }
    @Override
    public FoodCategory GetFoodCategoryById(int ID) {
        FoodCategory fc = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM foodcategory where Id = ? ";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, ID);
            System.out.println(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                fc = new FoodCategory(id, name);
                System.out.println(fc.toString());                
                return fc;
            }
        } catch (Exception e) {
        }
        return fc;
    }
    @Override
    public boolean GetInsertFoodCategory(String name) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "INSERT INTO foodcategory(Id, Name) \n"
                    + "SELECT COALESCE(MAX(Id), 0) + 1, '" + name + "' FROM foodcategory";
            PreparedStatement pst = conn.prepareCall(query);
//            pst.setString(1, name);
            System.out.println(query);
            ref = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ref > 0;
    }
    @Override
    public boolean GetDeleteFoodCategory(int id) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "DELETE FROM  foodcategory  WHERE Id = ?;";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, id);
            System.out.println(query);
            ref = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ref > 0;
    }
    @Override
    public boolean GetUpdateFoodCategory(int id, String name) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "UPDATE foodcategory AS f  SET f.Name = '"+ name + "' WHERE f.Id = ?";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, id);
            System.out.println(query);
            ref = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ref > 0;
    }
}
