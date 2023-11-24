package DAOImpl;

import DAOInterface.BillInterface;
import Model.Pay;
import java.util.List;
import JDBC.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import Model.Statistic;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
/**
 *
 * @author ASUS
 */
public class BillDAO extends UnicastRemoteObject implements BillInterface {

    public BillDAO() throws RemoteException{
        
    }
    @Override
    public List<Pay> LoadBillList(int iDTable) {
        List<Pay> billList = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT f.Name AS 'Tên món'"
                    + ", bi.Counts AS 'Số lượng'"
                    + ", f.Price AS 'Đơn giá'"
                    + ", f.Price * bi.Counts AS 'Thành tiền' \n"
                    + "FROM billinfo AS bi\n"
                    + "INNER JOIN bill AS b ON bi.IdBill = b.Id\n"
                    + "INNER JOIN food AS f ON bi.IdFood = f.Id\n"
                    + "WHERE b.IdTable = ? AND b.statu = 0;";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, iDTable);
            System.out.println(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Tên món");
                int count = rs.getInt("Số lượng");
                double price = rs.getDouble("Đơn giá");
                double totalPrice = rs.getDouble("Thành tiền");
                Pay bill = new Pay(name, count, price, totalPrice);
                System.out.println(bill.toString());
                billList.add(bill);
            }
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billList;
    }
    @Override
    public void InsertBill(int idTable) {
        int ketqua = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "INSERT INTO bill (Id, DateCheckIn, DateCheckOut, IdTable, Statu, Discount, TotalPrice)\n"
                            + "SELECT (COALESCE(MAX(Id), 0) + 1), NOW(), NULL, ?, 0 , 0 , 0 FROM bill";
            PreparedStatement pst = conn.prepareCall(query);
            System.out.println(query);
            pst.setInt(1, idTable);
            ketqua = pst.executeUpdate();
            System.out.println("đã update xong");
            if (ketqua > 0) {
                System.out.println("Nhập bill mới thành công");
            } else {
                System.out.println("Nhâp bill mới thất bại");
            }

        } catch (Exception e) {
        }

    }
    @Override
    public int getUncheckBillIDByTableID(int id) {
        String query = "SELECT * FROM bill WHERE IdTable = " + id + " AND statu = 0";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        System.out.println("Đang cố kết nối");
        try {
            conn = JDBCUtil.getConnection();
            pst = conn.prepareStatement(query);
            System.out.println("Đang lấy giá trị ở Bill");
            rs = pst.executeQuery();
            if (rs.next()) {
                int billID = rs.getInt("Id");
                System.out.println("Id ở BIllDAO " + billID);
                return billID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
    @Override
    public int GetMaxIDBill() {
        int max = 1;
        try {

            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT MAX(Id) as max FROM Bill";
            PreparedStatement pst = conn.prepareCall(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                max = rs.getInt("max");
            }
            System.out.println("MAX trong Bill " + max);
            pst.close();
            JDBCUtil.closeConnection(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }
    @Override
    public void CheckOut(int id, int discount, double totalPrice) {
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "UPDATE bill SET  DateCheckOut = NOW() , Statu = 1 , Discount = ? , TotalPrice = ? WHERE IdTable = ? AND Statu = 0 ;";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, discount);
            pst.setDouble(2, totalPrice);
            pst.setInt(3, id);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    @Override
    public List<Statistic> GetBillListByDate(Date checkIn, Date checkOut) {
        List<Statistic> list = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT t.Name AS 'Số bàn' , b.Discount  as 'Giảm giá(%)', b.TotalPrice as 'Tổng tiền' , b.DateCheckIn as 'Ngày nhận bàn', b.DateCheckOut as 'Ngày trả bàn' \n"
                    + "FROM bill AS b , tablefood AS t \n"
                    + "WHERE b.DateCheckIn >= ' " + checkIn +" '  \n"
                    + "AND b.DateCheckOut <= ' " + checkOut +" '  \n"
                    + "AND b.Statu = 1 \n"
                    + "AND t.Id  = b.IdTable";
            PreparedStatement pst = conn.prepareCall(query);
            ResultSet rs = pst.executeQuery();
            System.out.println(query);
            while(rs.next()){
                String name = rs.getString("Số bàn");
                int discount = rs.getInt("Giảm giá(%)");
                double totalPrice = rs.getDouble("Tổng tiền");
                Timestamp CheckIn = rs.getTimestamp("Ngày nhận bàn");
                Timestamp CheckOut = rs.getTimestamp("Ngày trả bàn");             
                Statistic st = new Statistic(name, discount, totalPrice, CheckIn, CheckOut);
                System.out.println(st.toString());
                list.add(st);
            }
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
