/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.FoodCategory;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Hữu Tiến
 */
public interface FoodCategoryInterface extends Remote {

    public List<FoodCategory> GetListCategory() throws RemoteException;

    public FoodCategory GetFoodCategoryById(int ID) throws RemoteException;

    public boolean GetInsertFoodCategory(String name) throws RemoteException;

    public boolean GetDeleteFoodCategory(int id) throws RemoteException;

    public boolean GetUpdateFoodCategory(int id, String name) throws RemoteException;
}
