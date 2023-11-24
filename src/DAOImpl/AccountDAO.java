package DAOImpl;

import DAOInterface.AccountInterface;
import JDBC.JDBCUtil;
import Model.Account;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author ASUS
 */
public class AccountDAO extends UnicastRemoteObject implements AccountInterface {

    public AccountDAO() throws RemoteException {
    }

    @Override
    public boolean Login(String userName, String passWord) {
        ResultSet rs = null;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "Select * From account Where "
                    + " UserName = ? "
                    + " AND PassWord = ? ";
            PreparedStatement pst = conn.prepareCall(sql);
            System.out.println(sql);
            pst.setString(1, userName);
            pst.setString(2, passWord);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Account GetAccByUserName(String username) {
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM account \n"
                    + "WHERE UserName = ? ";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String userName = rs.getString(1);
                String password = rs.getString(2);
                int type = rs.getInt(3);
                Account acc = new Account(userName, password, type);
                return acc;
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean UpdateAccount(String userName, String newUserName, String pass, String newPass) {
        int ketQua = 0;
        String newPassHashed;
        System.out.println("Gia tri cua newPass ở  dao " + newPass);
        if (newPass.equals("")) {
            newPassHashed = "";
        } else {
            System.out.println("newPass khac null nen duoc ma hoa");
            newPassHashed = hashPassword(newPass);
            System.out.println(newPassHashed);
        }

        String passHashed = hashPassword(pass);

        try {
            Connection conn = JDBCUtil.getConnection();
            String query2 = "CALL USP_UpdateAccount('" + userName + "', '" + newUserName + "', '" + passHashed + "', '" + newPassHashed + "')";
            String query = "CALL USP_UpdateAccount(? , ? , ? , ?)";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setString(1, userName);
            pst.setString(2, newUserName);
            pst.setString(3, passHashed);
            pst.setString(4, newPassHashed);
            System.out.println(query);
            System.out.println(query2);
            ketQua = pst.executeUpdate();
            if (ketQua > 0) {
                System.out.println("Cập nhật thành công !");
            } else {
                System.out.println("Cập nhật thất bại !");
            }
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketQua > 0;
    }

    private static String hashPassword(String password) {
        String generatedPassword = null;
        try {
            // Tạo một phiên bản SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Tạo mật khẩu băm
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            // chuyển đổi thành chuỗi thập lục
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    @Override
    public List<Account> GetListAcc() {
        List<Account> listAcc = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "SELECT * FROM account ;";
            PreparedStatement pst = conn.prepareCall(query);
            ResultSet rs = pst.executeQuery();
            System.out.println(query);
            while (rs.next()) {
                String userName = rs.getString(1);
                String password = rs.getString(2);
                int type = rs.getInt(3);
                Account acc = new Account(userName, password, type);
                listAcc.add(acc);
            }
            rs.close();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAcc;
    }

    @Override
    public boolean InsertAcc(String user, int type) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "INSERT INTO account (UserName, Password, Type)\n"
                    + "VALUES ('" + user + "', '5feceb66ffc86f38d952786c6d696c79c2dbc239dd4e91b46729d73a27fb57e9', ?);";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, type);
            System.out.println(query);
            ref = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
        }
        return ref > 0;
    }

    @Override
    public boolean UpdateAcc(int type, String user) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "UPDATE account\n"
                    + "SET  Type = ? \n"
                    + "WHERE UserName = '" + user + "';";
            PreparedStatement pst = conn.prepareCall(query);
            pst.setInt(1, type);
            ref = pst.executeUpdate();
            System.out.println(query);
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
        }
        return ref > 0;
    }

    @Override
    public boolean DeleteAcc(String user) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "DELETE FROM account \n"
                    + "WHERE UserName = '" + user + "';";
            PreparedStatement pst = conn.prepareCall(query);
            System.out.println(query);
            ref = pst.executeUpdate();
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
        }
        return ref > 0;
    }

    @Override
    public boolean ResetAcc(String user) {
        int ref = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String query = "UPDATE account\n"
                    + "SET Password = '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b'\n"
                    + "WHERE UserName = '" + user + "';";
            PreparedStatement pst = conn.prepareCall(query);
            ref = pst.executeUpdate();
            System.out.println(query);
            pst.close();
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
        }
        return ref > 0;
    }
}
