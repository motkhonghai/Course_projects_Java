/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.BillInfo;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Hữu Tiến
 */
public interface BillInfoInterface extends Remote {

    public List<BillInfo> GetListBillInfo(int id) throws RemoteException;

    public void InsertBillInfo(int IdBill, int IdFood, int Count) throws RemoteException;

    public void GetDeleteBillInfoByIdFood(int idFood) throws RemoteException;
}
