///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
// */
//package DAOInterface;
//
//import Model.Account;
//import Model.BillInfo;
//import Model.Food;
//import Model.FoodCategory;
//import Model.Pay;
//import Model.Statistic;
//import Model.TableFood;
//import java.rmi.Remote;
//import java.rmi.RemoteException;
//import java.sql.Date;
//import java.util.List;
//
///**
// *
// * @author Hữu Tiến
// */
//public interface DAOInterface extends Remote {
//    // Các phương thức của Account
//
//    public boolean Login(String userName, String passWord) throws RemoteException;
//
//    public Account GetAccByUserName(String username) throws RemoteException;
//
//    public boolean UpdateAccount(String userName, String newUserName, String pass, String newPass) throws RemoteException;
//
//    public List<Account> GetListAcc() throws RemoteException;
//
//    public boolean InsertAcc(String user, int type) throws RemoteException;
//
//    public boolean UpdateAcc(int type, String user) throws RemoteException;
//
//    public boolean DeleteAcc(String user) throws RemoteException;
//
//    public boolean ResetAcc(String user) throws RemoteException;
//
//    // Các phương thức của Bill
//    public List<Pay> LoadBillList(int iDTable) throws RemoteException;
//
//    public void InsertBill(int idTable) throws RemoteException;
//
//    public int getUncheckBillIDByTableID(int id) throws RemoteException;
//
//    public int GetMaxIDBill() throws RemoteException;
//
//    public void CheckOut(int id, int discount, double totalPrice) throws RemoteException;
//
//    public List<Statistic> GetBillListByDate(Date checkIn, Date checkOut) throws RemoteException;
//
//    // Các phương thức của BillInfo
//    public List<BillInfo> GetListBillInfo(int id) throws RemoteException;
//
//    public void InsertBillInfo(int IdBill, int IdFood, int Count) throws RemoteException;
//
//    public void GetDeleteBillInfoByIdFood(int idFood) throws RemoteException;
//
//    // Các phương thức của FoodCategory
//    public List<FoodCategory> GetListCategory() throws RemoteException;
//
//    public FoodCategory GetFoodCategoryById(int ID) throws RemoteException;
//
//    public boolean GetInsertFoodCategory(String name) throws RemoteException;
//
//    public boolean GetDeleteFoodCategory(int id) throws RemoteException;
//
//    public boolean GetUpdateFoodCategory(int id, String name) throws RemoteException;
//
//    // Các phương thức của Food
//    public List<Food> GetFoodByCategoryID(int ID)throws RemoteException;
//
//    public List<Food> GetListFood()throws RemoteException;
//
//    public boolean GetInsertFood(String name, int idCategory, double price)throws RemoteException;
//
//    public boolean GetDeleteFood(int idFood)throws RemoteException;
//
//    public boolean GetUpdateFood(int idFood, String name, int idCategory, double price)throws RemoteException;
//
//    public List<Food> SearchFoodByName(String Name)throws RemoteException;
//
//    public boolean GetDeleteFoodByIdCategory(int idCategory) throws RemoteException;
//            
//    // Các phương thức của Table
//    public List<TableFood> LoadTableList()throws RemoteException;
//
//    public boolean InserttableFood(String name, String status)throws RemoteException;
//
//    public boolean UpdatetableFood(int id, String name, String status)throws RemoteException;
//
//    public boolean DeletetableFood(int id)throws RemoteException;
//
//}
