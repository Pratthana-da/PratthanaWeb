/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class Addfood {

    private String foodname;
    private String type;
    private String[] taste; // array to store multiple checkboxes
    private String toppings; // string to store single selected radio button
    private double foodPrice;

    // getters and setters
    public String getFoodname() {return foodname;}
    public void setFoodname(String foodname) {this.foodname = foodname;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type; }
    public String[] getTaste() {return taste;}
    public void setTaste(String[] taste) {this.taste = taste;}
    public String getToppings() {return toppings;}
    public void setToppings(String toppings) {this.toppings = toppings;}
    public double getFoodPrice() {return foodPrice;}
    public void setFoodPrice(double foodPrice) {this.foodPrice = foodPrice;}

    public void setTaste(String Taste) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}