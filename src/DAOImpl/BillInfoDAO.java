
package DAOImpl;

import DAOInterface.BillInfoInterface;
import JDBC.JDBCUtil;
import Model.BillInfo;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class BillInfoDAO extends UnicastRemoteObject implements BillInfoInterface {

    public BillInfoDAO() throws RemoteException{
        
    }
    @Override
    public List<BillInfo> GetListBillInfo(int id) {
        List<BillInfo> listBillInfo = new ArrayList<>();
        String query = "SELECT * FROM billinfo WHERE Id = ?";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, id);
            System.out.println(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt(1);
                int IdBill = rs.getInt(2);
                int IdFood = rs.getInt(3);
                int Count = rs.getInt(4);
                BillInfo billInfo = new BillInfo(Id, IdBill, IdFood);
                System.out.println(billInfo.toString());
                listBillInfo.add(billInfo);
            }
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBillInfo;
    }
    @Override
    public void InsertBillInfo(int IdBill, int IdFood, int Count) {
        String query = "CALL USP_InsertBillInfo(?, ?, ?);";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, IdBill);
            pst.setInt(2, IdFood);
            pst.setInt(3, Count);
            System.out.println(query);
            pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void GetDeleteBillInfoByIdFood(int idFood) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "DELETE FROM  billinfo  WHERE IdFood = ?;";
            PreparedStatement pst = conn.prepareCall(query);
            System.out.println(query);
            pst.setInt(1, idFood);
            ref = pst.executeUpdate();
            if (ref > 0) {
                System.out.println("Xóa thành công Bill");
            } else {
                System.out.println("Có lỗi khi xóa Bill");
            }
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
