/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.Pay;
import Model.Statistic;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Hữu Tiến
 */
public interface BillInterface extends Remote {
    public List<Pay> LoadBillList(int iDTable) throws RemoteException;

    public void InsertBill(int idTable) throws RemoteException;

    public int getUncheckBillIDByTableID(int id) throws RemoteException;

    public int GetMaxIDBill() throws RemoteException;

    public void CheckOut(int id, int discount, double totalPrice) throws RemoteException;

    public List<Statistic> GetBillListByDate(Date checkIn, Date checkOut) throws RemoteException;
}
