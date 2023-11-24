/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.Food;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Hữu Tiến
 */
public interface FoodInterface extends Remote {

    public List<Food> GetFoodByCategoryID(int ID) throws RemoteException;

    public List<Food> GetListFood() throws RemoteException;

    public boolean GetInsertFood(String name, int idCategory, double price) throws RemoteException;

    public boolean GetDeleteFood(int idFood) throws RemoteException;

    public boolean GetUpdateFood(int idFood, String name, int idCategory, double price) throws RemoteException;

    public List<Food> SearchFoodByName(String Name) throws RemoteException;

    public boolean GetDeleteFoodByIdCategory(int idCategory) throws RemoteException;
}
