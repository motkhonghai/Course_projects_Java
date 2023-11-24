/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAOInterface.AccountInterface;
import Model.Account;
import Client.LoginServer;
import Client.OrderServer;
import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author Hữu Tiến
 */
public class LoginController {

    static String urlConnect = "rmi://localhost:2004/Login";

    public static boolean Login(String userName, String passWord) {
        try {
            AccountInterface userRmi = (AccountInterface) Naming.lookup(urlConnect);
            return userRmi.Login(userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void getAccountServer(String userName, String hashed) {
        try {
            if (Login(userName, hashed)) {
                AccountInterface accRmi = (AccountInterface) Naming.lookup(urlConnect);
                Account loginAccount = accRmi.GetAccByUserName(userName);
                OrderServer order = new OrderServer(loginAccount);
                order.setVisible(true);
                LoginServer log = new LoginServer();
                log.dispose();
            } else {
                JOptionPane.showConfirmDialog(null, "Bạn đã nhập sai tên tài khoản hoặc sai mật khẩu !", "Thông báo", JOptionPane.YES_OPTION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
