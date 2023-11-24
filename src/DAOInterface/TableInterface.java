/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.TableFood;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Hữu Tiến
 */
public interface TableInterface extends Remote {

    public List<TableFood> LoadTableList() throws RemoteException;

    public boolean InserttableFood(String name, String status) throws RemoteException;

    public boolean UpdatetableFood(int id, String name, String status) throws RemoteException;

    public boolean DeletetableFood(int id) throws RemoteException;
    
    public void SwitchTable (int id1, int id2) throws RemoteException;
}
