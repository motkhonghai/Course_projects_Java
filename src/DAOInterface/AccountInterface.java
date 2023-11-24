/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.Account;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Hữu Tiến
 */
public interface AccountInterface extends Remote {

    public boolean Login(String userName, String passWord) throws RemoteException;

    public Account GetAccByUserName(String username) throws RemoteException;

    public boolean UpdateAccount(String userName, String newUserName, String pass, String newPass) throws RemoteException;

    public List<Account> GetListAcc() throws RemoteException;

    public boolean InsertAcc(String user, int type) throws RemoteException;

    public boolean UpdateAcc(int type, String user) throws RemoteException;

    public boolean DeleteAcc(String user) throws RemoteException;

    public boolean ResetAcc(String user) throws RemoteException;
}
