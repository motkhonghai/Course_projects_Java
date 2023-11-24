/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class BillInfo implements Serializable{
    private int id;
    private int idBill;
    private int idFood;

    public BillInfo(int id, int idBill, int idFood) {
        this.id = id;
        this.idBill = idBill;
        this.idFood = idFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    @Override
    public String toString() {
        return "BillInfo{" + "id=" + id + ", idBill=" + idBill + ", idFood=" + idFood + '}';
    }
    
    
}
