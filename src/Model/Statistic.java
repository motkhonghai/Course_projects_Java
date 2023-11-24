/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class Statistic implements Serializable{
    private String name;
    private int discount;
    private double totalPrice;
    private Timestamp checkIn;
    private Timestamp checkOut;

    public Statistic(String name, int discount, double totalPrice, Timestamp checkIn, Timestamp checkOut) {
        this.name = name;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Timestamp checkIn) {
        this.checkIn = checkIn;
    }

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Statistic{" + "name=" + name + ", discount=" + discount + ", totalPrice=" + totalPrice + ", checkIn=" + checkIn + ", checkOut=" + checkOut + '}';
    }
 
}
