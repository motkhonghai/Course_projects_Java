/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

//import Client.InformationClient;
import static Controller.LoginController.urlConnect;
import DAOInterface.AccountInterface;
import Model.Account;
import java.rmi.Naming;
import javax.swing.JOptionPane;

/**
 *
 * @author Hữu Tiến
 */
public class InfomationController {
    public static void UpdateAccount(String user, String newUser, String pass, String newPass, String newPass2) {
        try {
            AccountInterface userRmi = (AccountInterface) Naming.lookup("rmi://localhost:2004/Login");
            Account loginAccount = userRmi.GetAccByUserName(user);
            if (!newPass.equals(newPass2)) {
                JOptionPane.showConfirmDialog(null, "Vui lòng nhập lại đúng mật khẩu mới !", "Thông báo", JOptionPane.OK_OPTION);
            } else {
                if (userRmi.UpdateAccount(user, newUser, pass, newPass)) {
                    int ref = JOptionPane.showConfirmDialog(null, "Cập nhật thành công !", "Thông báo", JOptionPane.OK_OPTION);
                    if (ref == JOptionPane.OK_OPTION) {
//                        InformationClient inf = new InformationClient(loginAccount);
//                        inf.dispose();
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "Vui lòng điền đúng mật khẩu ", "Thông báo", JOptionPane.OK_OPTION);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
