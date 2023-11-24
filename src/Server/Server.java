/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

/**
 *
 * @author Hữu Tiến
 */
import DAOImpl.AccountDAO;
import DAOImpl.BillDAO;
import DAOImpl.BillInfoDAO;
import DAOImpl.FoodCategoryDAO;
import DAOImpl.FoodDAO;
import DAOImpl.TableDAO;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author framgiavn
 */
public class Server {

    public static void main(String args[]) {
        try {
            LocateRegistry.createRegistry(2004);
            // Đăng ký đối tượng này với rmiregistry
            Naming.bind("rmi://localhost:2004/Login", new AccountDAO());
            Naming.bind("rmi://localhost:2004/Bill", new BillDAO());
            Naming.bind("rmi://localhost:2004/BillInfo", new BillInfoDAO());
            Naming.bind("rmi://localhost:2004/FoodCategory", new FoodCategoryDAO());
            Naming.bind("rmi://localhost:2004/Food", new FoodDAO());
            Naming.bind("rmi://localhost:2004/Table", new TableDAO());
            System.out.println("INFO: RMI Server started !!!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
