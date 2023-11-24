
package DAOImpl;

import DAOInterface.TableInterface;
import java.util.List;
import Model.TableFood;
import JDBC.JDBCUtil;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class TableDAO extends UnicastRemoteObject implements TableInterface{
    
    public TableDAO() throws RemoteException{
        
    }
    @Override
    public List<TableFood> LoadTableList() {
        List<TableFood> tableList = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "Select * From tablefood";
            PreparedStatement pst = conn.prepareStatement(query);
            System.out.println(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int id  = rs.getInt("Id");
                String name  = rs.getString("Name");
                String status  = rs.getString("Statu");
                TableFood tableFood = new TableFood(id, name, status);
                tableList.add(tableFood);
            }
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tableList;
    }
    @Override
    public boolean InserttableFood(String name, String status){
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "INSERT INTO tablefood (Id, Name, Statu)\n" +
                            "SELECT COALESCE(MAX(Id), 0) + 1, '"+ name +"', '"+status+"' FROM tablefood;";
            PreparedStatement pst = conn.prepareCall(query);
//            pst.setString(1, name);
            System.out.println(query);
            ref  = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
        }
        return ref > 0;
    }
    @Override
    public boolean UpdatetableFood(int id, String name, String status){
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "UPDATE tablefood AS t SET t.Name = '"+name+"',t.Statu='"+status+"' WHERE Id = ?";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, id);
            ref  = pst.executeUpdate();
            System.out.println(query);
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
        }
        return ref > 0;
    }
    @Override
    public boolean DeletetableFood(int id){
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "DELETE FROM  tablefood  WHERE Id = ?;";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, id);
            System.out.println(query);
            ref  = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
        }
        return ref > 0;
    }
    
    @Override
    public void SwitchTable (int id1, int id2){
        try{
            Connection conn = JDBCUtil.getConnection();
            String query = "call USP_SwitchTable(? , ?)";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, id1);
            pst.setInt(2, id2);
            pst.executeQuery();
//            pst.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
